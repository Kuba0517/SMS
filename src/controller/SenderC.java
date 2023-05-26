package controller;

import events.AddButtonListenerMessage;
import events.Device;
import events.RemoveListener;
import events.ViewUpdateListener;
import models.Message;
import models.NetworkDevice;
import models.SenderM;
import models.VBDM;

public class SenderC implements RemoveListener, AddButtonListenerMessage<Message>, ViewUpdateListener<SenderM> {
    private SenderM model;
    private ViewUpdateListener<SenderM> view;
    private NetworkC networkC;

    public SenderC(SenderM model, NetworkC networkC){
        this.model = model;
        this.networkC = networkC;
        model.setViewUpdateListener(this);
    }

    public void setView(ViewUpdateListener<SenderM> view) {
        this.view = view;
        model.setViewUpdateListener(view);
    }

    @Override
    public void add(Message message) {
        VBDM vbdm = new VBDM(message, this, networkC);
        model.addVBD(vbdm);
    }


    @Override
    public void remove(Device device) {
        model.removeVBD((VBDM) device);
    }

    @Override
    public void updateView(SenderM item) {
        System.out.println("Aktualizacjjjjjajajaja");
        if(view != null){
            view.updateView(item);
            System.out.println("Aktualizacja widoku");
        }
    }
}
