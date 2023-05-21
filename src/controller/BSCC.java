package controller;

import events.NetworkDevice;
import events.ViewUpdateListener;
import graphics.BSCG;
import models.BSCM;
import models.BTSM;
import models.ReceiverM;
import models.SenderM;

public class BSCC implements NetworkDevice, ViewUpdateListener<BSCM> {
    private BSCM model;
    private ViewUpdateListener<BSCM> view;

    public BSCC(BSCM model) {
        this.model = model;
        model.addViewUpdateListener(this);
    }

    @Override
    public String getNumber() {
        return model.getNumber();
    }

    public void setView(ViewUpdateListener<BSCM> view) {
        this.view = view;
        model.addViewUpdateListener(view);
    }

    @Override
    public String smsTransfered() {
        return model.getSmsTransfered();
    }

    @Override
    public String getPendingMessage() {
        return Integer.toString(model.getPendingMessage());
    }

    @Override
    public void updateView(BSCM item) {
        if(view != null){
            view.updateView(item);
        }
    }
}

