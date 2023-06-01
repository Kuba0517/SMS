package controller;

import events.*;
import models.*;

import java.util.ArrayList;
import java.io.*;

public class NetworkC implements AddButtonListener, RemoveButtonListener, ViewUpdateListener<NetworkM>, Senders, Closing {
    private NetworkM model;
    private ViewUpdateListener<NetworkM> view;

    private SenderM senderM;
    private ArrayList<VRDM> vrdmList;

    public NetworkC(NetworkM model, ReceiverM receiverM, SenderM senderM){
        this.model = model;
        this.senderM = senderM;
        this.vrdmList = receiverM.getVRDList();

        model.addViewUpdateListener(this);
        for (BTSM btsm: model.getStartBtsm().getBTSMS()) {
            btsm.setStartBTS(true);
            btsm.setMessageSender(this);
            btsm.startThread();
        }

        for(BSCSectionModel bscSectionModel: model.getBscmList()) {
            for (BSCM bscm : bscSectionModel.getBSCMS()) {
                bscm.setMessageSender(this);
                bscm.startThread();
            }
        }

        for(BTSM btsm : model.getEndBtsm().getBTSMS()){
            btsm.setStartBTS(false);
            btsm.setMessageSender(this);
            btsm.startThread();
        }


    }


    private synchronized BTSM findBestBts(BTSSectionModel bts){
        boolean isFiveMessages = true;
        BTSM reliableBTS = null;
        for(BTSM btsm : bts.getBTSMS()){
            if(btsm.getPendingMessage() < 5){
                isFiveMessages = false;
            }
            if(reliableBTS == null || btsm.getPendingMessage() < reliableBTS.getPendingMessage()){
                reliableBTS = btsm;
            }
        }
        if(isFiveMessages){
            bts.addBTSM();
            BTSM newBTS = bts.getBTSMS().get(bts.getBTSMS().size()-1);
            newBTS.setMessageSender(this);
            newBTS.setStartBTS(bts.getBTSMS().get(0).isStartBTS());
            newBTS.startThread();
            if(newBTS.getPendingMessage() < reliableBTS.getPendingMessage()){
                reliableBTS = newBTS;
            }
        }
        return reliableBTS;
    }

    private synchronized BSCM findBestBsc(BSCSectionModel bsc){
        if(bsc.getBSCMS().size() == 0){
            return null;
        }
        boolean isFiveMessages = true;
        BSCM reliableBSC = null;
        for(BSCM bscm : bsc.getBSCMS()){
            if(bscm.getPendingMessage() < 5){
                isFiveMessages = false;
            }
            if(reliableBSC == null || bscm.getPendingMessage() < reliableBSC.getPendingMessage()){
                reliableBSC = bscm;
            }
        }
        if(isFiveMessages){
            bsc.addBSCM();
            BSCM newBSC = bsc.getBSCMS().get(bsc.getBSCMS().size()-1);
            newBSC.setMessageSender(this);
            newBSC.startThread();
            if(newBSC.getPendingMessage() < reliableBSC.getPendingMessage()){
                reliableBSC = newBSC;
            }
        }
        return reliableBSC;
    }

    public void setView(ViewUpdateListener<NetworkM> view) {
        this.view = view;
        model.addViewUpdateListener(view);
        updateView(model);
    }

    @Override
    public void sendMessage(Message message, String deviceNumber) {
        BTSM reliableBTS = findBestBts(model.getStartBtsm());
        reliableBTS.addMessage(message);
    }

    @Override
    public void sendMessageBTS(BTSM btsm){
        if (btsm.isStartBTS()) {
            BSCM reliableBSC = findBestBsc(model.getBscmList().get(0));
            reliableBSC.addMessage(btsm.getMessagePoll());
        } else {
                String deviceNumber = btsm.getMessagePeek().getRecipient();
                VRDM receipent = null;
                for(VRDM vrdm : vrdmList){
                    if(Integer.toString(vrdm.getNumber()).equals(deviceNumber)){
                        receipent = vrdm;
                        break;
                    }
                }
                try {
                    if (receipent != null) {
                        receipent.messageTo(btsm.getMessagePoll());
                    } else {
                        throw new NoVRDException("Nie ma takiego urządzenia odbiorczego");
                    }
                }
                catch (NoVRDException x){
                    x.printStackTrace();
            }
        }
    }


    @Override
    public void sendMessageBSC(BSCM bscm){
        System.out.println("Numer w kolejce: " + bscm.getNumberInOrder());
        if(model.getBscmList().size() - 1 > bscm.getNumberInOrder()) {
            BSCM reliableBSC = findBestBsc(model.getBscmList().get(bscm.getNumberInOrder() + 1));
            reliableBSC.addMessage(bscm.getMessagePoll());
        }
        else {
            BTSM reliableBTS = findBestBts(model.getEndBtsm());
            reliableBTS.addMessage(bscm.getMessagePoll());
        }
    }


    public void saveOnClose() {
        try (FileOutputStream fos = new FileOutputStream("save.bin")) {
            for (VBDM device : senderM.getVBDList()) {
                int messagesTransfered = device.getTransferedSMS();
                String pdu = device.getMessage().getPdu();


                byte[] pduBytes = hexStringToByteArray(pdu);
                fos.write(pduBytes);

                fos.write(messagesTransferedBytes(messagesTransfered));

                fos.write('\n');
            }
        } catch (IOException e) {
            System.err.println("Nie można zapisać informacji");
        }
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    private byte[] messagesTransferedBytes(int x) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((x >> 24) & 0xFF);
        bytes[1] = (byte) ((x >> 16) & 0xFF);
        bytes[2] = (byte) ((x >> 8) & 0xFF);
        bytes[3] = (byte) (x & 0xFF);
        return bytes;
    }


    @Override
    public void sendAllMessages() {
        for (VBDM vbdm : senderM.getVBDList()) {
            vbdm.getThread().interrupt();
        }
        for (BTSM btsm : model.getStartBtsm().getBTSMS()) {
            btsm.getThread().interrupt();
        }
        for (BSCSectionModel bscSectionModel : model.getBscmList()) {
            for (BSCM bscm : bscSectionModel.getBSCMS()) {
                bscm.getThread().interrupt();
            }
        }
        for (BTSM btsm : model.getEndBtsm().getBTSMS()) {
            btsm.getThread().interrupt();
        }

        boolean allThreadsDead = false;
        while (!allThreadsDead) {
            allThreadsDead = true;

            for (VBDM vbdm : senderM.getVBDList()) {
                if (vbdm.getThread().isAlive()) {
                    allThreadsDead = false;
                    break;
                }
            }

            if (allThreadsDead) {
                for (BTSM btsm : model.getStartBtsm().getBTSMS()) {
                    if (btsm.getThread().isAlive()) {
                        allThreadsDead = false;
                        break;
                    }
                }
            }

            if (allThreadsDead) {
                for (BSCSectionModel bscSectionModel : model.getBscmList()) {
                    for (BSCM bscm : bscSectionModel.getBSCMS()) {
                        if (bscm.getThread().isAlive()) {
                            allThreadsDead = false;
                            break;
                        }
                    }
                    if (!allThreadsDead) {
                        break;
                    }
                }
            }

            if (allThreadsDead) {
                for (BTSM btsm : model.getEndBtsm().getBTSMS()) {
                    if (btsm.getThread().isAlive()) {
                        allThreadsDead = false;
                        break;
                    }
                }
            }

            if (!allThreadsDead) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        for (BTSM btsm: model.getStartBtsm().getBTSMS()) {
            btsm.getThread().interrupt();
            Message message = btsm.getMessagePeek();
            if(message != null) {
                VRDM vrdmDest = null;
                for (VRDM vrdm : vrdmList) {
                    if (message.getRecipient().equals(Integer.toString(vrdm.getNumber()))) {
                        vrdmDest = vrdm;
                    }
                }
                try {
                    if (vrdmDest != null) {
                        vrdmDest.messageTo(btsm.getMessagePoll());
                    } else {
                        throw new NoVRDException("Nie ma takiego urządzenia odbiorczego");
                    }
                } catch (NoVRDException x) {
                    x.printStackTrace();
                }
            }
        }

        for(BSCSectionModel bscSectionModel: model.getBscmList()) {
            for (BSCM bscm : bscSectionModel.getBSCMS()) {
                bscm.getThread().interrupt();
                Message message = bscm.getMessagePeek();
                if (message != null) {
                    VRDM vrdmDest = null;
                    for (VRDM vrdm : vrdmList) {
                        if (message.getRecipient().equals(Integer.toString(vrdm.getNumber()))) {
                            vrdmDest = vrdm;
                        }
                    }
                    try {
                        if (vrdmDest != null) {
                            vrdmDest.messageTo(bscm.getMessagePoll());
                        } else {
                            throw new NoVRDException("Nie ma takiego urządzenia odbiorczego");
                        }
                    } catch (NoVRDException x) {
                        x.printStackTrace();
                    }
                }
            }
        }

        for (BTSM btsm: model.getEndBtsm().getBTSMS()) {
            btsm.getThread().interrupt();
            Message message = btsm.getMessagePeek();
            if(message != null) {
                VRDM vrdmDest = null;
                for (VRDM vrdm : vrdmList) {
                    if (message.getRecipient().equals(Integer.toString(vrdm.getNumber()))) {
                        vrdmDest = vrdm;
                    }
                }
                try {
                    if (vrdmDest != null) {
                        vrdmDest.messageTo(btsm.getMessagePoll());
                    } else {
                        throw new NoVRDException("Nie ma takiego urządzenia odbiorczego");
                    }
                } catch (NoVRDException x) {
                    x.printStackTrace();
                }
            }
        }
        saveOnClose();
        System.out.println("WSZYSTKIE WIADOMOŚCI ZOSTAŁY WYSŁANE. ZAMKYAM PROGRAM !");
    }

    @Override
    public void add() {
        model.addBscm();
    }

    @Override
    public void remove() {
        if(model.getBscmList().size() > 1) {
            model.removeBscm();
        }
    }

    @Override
    public void updateView(NetworkM item) {
        if(view != null){
            view.updateView(item);
            System.out.println("Aktualizacja widoku");
        }
    }

}

