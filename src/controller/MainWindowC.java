package controller;

import events.MessageReceiver;
import models.BTSM;
import models.MainWindowM;
import models.Message;
import models.NetworkM;
import events.NetworkGettable;

public class MainWindowC implements MessageReceiver {
    private NetworkGettable network;
    private MainWindowM model;
    public MainWindowC(MainWindowM model, NetworkGettable network){
        this.model = model;
        this.network = network;
        model.addNetwork(network.getNetworkM());
    }

    @Override
    public void receiveMessage(Message message, String deviceNumber) {
        BTSM reliableBTS = model.getStartingBTS().get(0);
        for (BTSM bts:
                model.getStartingBTS()) {
            System.out.println(bts.getNumber());
            if(bts.getPendingMessage() < reliableBTS.getPendingMessage()){
                reliableBTS = bts;
            }
        }
        reliableBTS.addMessage(message);
        System.out.println("Wiadomość od urządzeina " + deviceNumber + " została przekazana do BTS o numerze " + reliableBTS.getNumber());
    }
}
