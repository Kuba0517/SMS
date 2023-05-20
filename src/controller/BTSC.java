package controller;

import events.NetworkDevice;
import events.ViewUpdateListener;
import models.BTSM;
import models.Message;
import models.ReceiverM;

public class BTSC implements NetworkDevice, ViewUpdateListener<BTSM> {
    private BTSM model;
    private ViewUpdateListener<BTSM> view;

    public BTSC(BTSM model) {
        this.model = model;
        model.addViewUpdateListener(this);
    }

    public void setView(ViewUpdateListener<BTSM> view) {
        this.view = view;
        model.addViewUpdateListener(view);
    }

    @Override
    public String getNumber() {
        return model.getNumber();
    }

    @Override
    public String smsTransfered() {
        return model.getTransferedMessage();
    }

    @Override
    public String getPendingMessage() {
        return Integer.toString(model.getPendingMessage());
    }

    @Override
    public void updateView(BTSM item) {
        if(view != null){
            view.updateView(item);
        }
    }
}
