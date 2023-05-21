package controller;

import events.*;
import models.*;

public class MainWindowC implements MessageReceiver, MessageReceiverBTS , ViewUpdateListener<MainWindowM>, MessageReceiverBSC {
    private ViewUpdateListener<MainWindowM> view;
    private NetworkGettable network;
    private MainWindowM model;
    public MainWindowC(MainWindowM model, NetworkGettable network){
        this.model = model;
        this.network = network;
        model.addNetwork(network.getNetworkM());
        network.getNetworkM().getStartBtsm().setMessageReceiver(this);
        for(BSCM bscm : network.getNetworkM().getBscmList()){
            bscm.setMessageReceiver(this);
        }
    }

    public void setView(ViewUpdateListener<MainWindowM> view) {
        this.view = view;
        model.addViewUpdateListener(view);
    }

    public void btsCheck() {
        if (network.getNetworkM().getStartBtsm().getPendingMessage() > 5) {
            model.addNetwork(new NetworkM());
            System.out.println("DODANO NOWĄ SIEĆ");
        }
    }


    @Override
    public void receiveMessage(Message message, String deviceNumber) {
        btsCheck();
        BTSM reliableBTS = model.getStartingBTS().get(0);
        for (BTSM bts:
                model.getStartingBTS()) {
            System.out.println(bts.getNumber());
            if(bts.getPendingMessage() < reliableBTS.getPendingMessage()){
                reliableBTS = bts;
            }
        }
        reliableBTS.addMessage(message);
        reliableBTS.start();
        System.out.println("Wiadomość od urządzeina " + deviceNumber + " została przekazana do BTS o numerze " + reliableBTS.getNumber());
    }

    @Override
    public void receiveMessageBTS(Message message, String deviceNumber) {
        btsCheck();
        BSCM reliableBSC = network.getNetworkM().getBscmList().get(0);
        for(BSCM bsc: network.getNetworkM().getBscmList()){
            if(bsc.getPendingMessage() < reliableBSC.getPendingMessage()){
                reliableBSC = bsc;
            }
        }
        reliableBSC.addMessage(message);
        System.out.println("Wiadomość od urządzeina " + deviceNumber + " została przekazana do BSC o numerze " + reliableBSC.getNumber());
    }

    @Override
    public void receiveMessageBSC(Message message, String deviceNumber) {
        BSCM reliableBSC = null;
        for (BSCM bsc:
                network.getNetworkM().getBscmList()) {
            if(!bsc.getNumber().equals(deviceNumber)){
                reliableBSC = bsc;
                break;
            }
        }
        for(BSCM bsc: network.getNetworkM().getBscmList()){
            if(bsc.getPendingMessage() < reliableBSC.getPendingMessage()){
                reliableBSC = bsc;
            }
        }
        reliableBSC.addMessage(message);
    }

    @Override
    public void updateView(MainWindowM item) {
        if(view != null){
            view.updateView(item);
        }
    }

}
