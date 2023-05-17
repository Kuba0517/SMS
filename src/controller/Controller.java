package controller;

import models.VBDM;
import graphics.ReceiverG;

public class Controller {
    private VBDM vbdm;
    private ReceiverG receiver;

    public Controller(VBDM vbdm, ReceiverG receiver) {
        this.vbdm = vbdm;
        this.receiver = receiver;
    }

//    public void sendMessage(String message) {
//        vbdm.send(message);
//    }

//    public void receiveMessage(String message) {
//        receiver.receive(message);
//    }
}

