package controller;

import events.*;
import models.*;

import java.util.Random;

public class NetworkC implements AddButtonListener, RemoveButtonListener, ViewUpdateListener<NetworkM>, MessageSender {
    private NetworkM model;
    private ViewUpdateListener<NetworkM> view;

    public NetworkC(NetworkM model){
        this.model = model;
        model.addViewUpdateListener(this);

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
            findBestBts(bts);
        }
        return reliableBTS;
    }

    private BSCM findBestBsc(BSCSectionModel bsc){
        if(bsc.getBSCMS().size() == 0){
            return null;
        }
        BSCM reliableBSC = bsc.getBSCMS().get(0);
        for(BSCM bscm : bsc.getBSCMS()){
            if(bscm.getPendingMessage() < reliableBSC.getPendingMessage()){
                reliableBSC = bscm;
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
        System.out.println("Wiadomość od urządzeina " + deviceNumber + " została przekazana do BTS o numerze " + reliableBTS.getNumber());

        sendMessageBTStoBSC(reliableBTS,message, deviceNumber);
        sendMessageBSCtoBSC(message, deviceNumber);
    }

    public void sendMessageBTStoBSC(BTSM btsm,Message message, String deviceNumber){
        new Thread (() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BSCM reliableBSC = findBestBsc(model.getBscmList().get(0));
            reliableBSC.addMessage(btsm.getMessage());
            System.out.println("Wiadomość od urządzeina " + deviceNumber + " została przekazana do BSC o numerze " + reliableBSC.getNumber());
        }).start();
    }

    public void sendMessageBSCtoBSC(Message message, String deviceNumber){
        Random rand = new Random();
            new Thread(() -> {
                for(int i = 1; i < model.getBscmList().size(); i++) {
                    try {
                        Thread.sleep((rand.nextInt(11) + 5) * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    BSCM reliableBSC = findBestBsc(model.getBscmList().get(i));
                    reliableBSC.addMessage(message);
                    System.out.println("Wiadomość od urządzeina " + deviceNumber + " została przekazana do BSC o numerze " + reliableBSC.getNumber());
                }}).start();
    }

    @Override
    public void add() {
        model.addBscm();
    }

    @Override
    public void remove() {
        model.removeBscm();
    }

    @Override
    public void updateView(NetworkM item) {
        if(view != null){
            view.updateView(item);
            System.out.println("Aktualizacja widoku");
        }
    }

}

