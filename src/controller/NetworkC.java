package controller;

import events.*;
import models.*;

import java.util.ArrayList;
import java.util.Random;

public class NetworkC implements AddButtonListener, RemoveButtonListener, ViewUpdateListener<NetworkM>, Senders {
    private NetworkM model;
    private ViewUpdateListener<NetworkM> view;

    private ArrayList<VRDM> vrdmList;
    private MessageListener messageListener;

    public NetworkC(NetworkM model, ReceiverM receiverM){
        this.model = model;
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


    private BTSM findBestBts(BTSSectionModel bts){
        BTSM reliableBTS = bts.getBTSMS().get(0);
        boolean isFiveMessages = true;
        for(BTSM btsm : bts.getBTSMS()){
            if(btsm.getPendingMessage() < 5){
                isFiveMessages = false;
            }
            if(btsm.getPendingMessage() < reliableBTS.getPendingMessage()){
                reliableBTS = btsm;
            }
        }
        if(isFiveMessages){
            bts.addBTSM();
            bts.getBTSMS().get(bts.getBTSMS().size()-1).setMessageSender(this);
            bts.getBTSMS().get(bts.getBTSMS().size()-1).setStartBTS(bts.getBTSMS().get(0).isStartBTS());
            bts.getBTSMS().get(bts.getBTSMS().size()-1).startThread();
            findBestBts(bts);
        }
        return reliableBTS;
    }

    private BSCM findBestBsc(BSCSectionModel bsc){
        if(bsc.getBSCMS().size() == 0){
            return null;
        }
        BSCM reliableBSC = bsc.getBSCMS().get(0);
        boolean isFiveMessages = true;
        for(BSCM bscm : bsc.getBSCMS()){
            if(bscm.getPendingMessage() < 5){
                isFiveMessages = false;
            }
            if(bscm.getPendingMessage() < reliableBSC.getPendingMessage()){
                reliableBSC = bscm;
            }
        }
        if(isFiveMessages){
            bsc.addBSCM();
            bsc.getBSCMS().get(bsc.getBSCMS().size()-1).setMessageSender(this);
            bsc.getBSCMS().get(bsc.getBSCMS().size()-1).startThread();
            findBestBsc(bsc);
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
        System.out.println("Wiadomość od urządzeina " + deviceNumber + " została przekazana do BTS o numerze " + reliableBTS.getNumber());

    }

    @Override
    public void sendMessageBTS(BTSM btsm){
        if (btsm.isStartBTS()) {
            BSCM reliableBSC = findBestBsc(model.getBscmList().get(0));
            reliableBSC.addMessage(btsm.getMessagePoll());
            System.out.println("Wiadomość od urządzeina " + btsm.getNumber() + " została przekazana do BSC o numerze " + reliableBSC.getNumber());
        } else {
            if (vrdmList.size() == 0) {
                System.out.println("Nie ma urządzenia odbierającego wiadomość");
                return;
            } else {
                this.messageListener = vrdmList.get(0); // pobieramy wiadomość w modelu
                messageListener.messageTo(btsm.getMessagePoll());
            }
        }
    }


    @Override
    public void sendMessageBSC(BSCM bscm){
        System.out.println("Numer w kolejce: " + bscm.getNumberInOrder());
        if(model.getBscmList().size() - 1 > bscm.getNumberInOrder()) {
            BSCM reliableBSC = findBestBsc(model.getBscmList().get(bscm.getNumberInOrder() + 1));
            reliableBSC.addMessage(bscm.getMessagePoll());
            System.out.println("Wiadomość od urządzeina " + bscm.getNumber() + " została przekazana do BSC o numerze " + reliableBSC.getNumber());
        }
        else {
            BTSM reliableBTS = findBestBts(model.getEndBtsm());
            reliableBTS.addMessage(bscm.getMessagePoll());
            System.out.println("Wiadomość od urządzeina " + bscm.getNumber() + " została przekazana do BTS o numerze " + reliableBTS.getNumber());
        }
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

