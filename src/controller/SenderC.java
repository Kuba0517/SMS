package controller;

import events.AddButtonListenerMessage;
import events.Device;
import events.RemoveListener;
import events.ViewUpdateListener;
import models.Message;
import models.SenderM;
import models.VBDM;

public class SenderC implements RemoveListener, AddButtonListenerMessage<Message>, ViewUpdateListener<SenderM> {
    private SenderM model;
    private ViewUpdateListener<SenderM> view;
    private MainWindowC mainWindowC;

    public SenderC(SenderM model, MainWindowC mainWindowC){
        this.model = model;
        model.setViewUpdateListener(this);
        this.mainWindowC = mainWindowC;
    }

    public void setView(ViewUpdateListener<SenderM> view) {
        this.view = view;
        model.setViewUpdateListener(view);
    }

    @Override
    public void add(Message message) {
        VBDM vbdm = new VBDM(message, this, mainWindowC);
        model.addVBD(vbdm);
        System.out.println("Dodajemy VBD");
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
