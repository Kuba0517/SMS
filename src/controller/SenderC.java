package controller;

import events.AddButtonListener;
import events.ViewUpdateListener;
import models.Message;
import models.SenderM;
import models.VBDM;

public class SenderC implements AddButtonListener {
    private SenderM model;
    private ViewUpdateListener<VBDM> view;

    public SenderC(SenderM model){
        this.model = model;
    }

    public void setView(ViewUpdateListener<VBDM> view) {
        this.view = view;
        model.addViewUpdateListener(view);
    }

    @Override
    public void addItem(Message message) {
        VBDM vbdm = new VBDM(message, 10);
        model.addVBD(vbdm);
        System.out.println("Dodajemy VBD");
    }
}
