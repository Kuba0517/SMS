package controller;

import events.NetworkDeviceInter;
import events.ViewUpdateListener;
import models.BSCM;

public class BSCC implements ViewUpdateListener<BSCM>, NetworkDeviceInter {
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
    public String getSmsTransfered() {
        return Integer.toString(model.getTransferedSms());
    }

    @Override
    public String getPendingMessage() {
        return Integer.toString(model.getPendingMessage());
    }

    @Override
    public void updateView(BSCM item) {
        if(view != null){
            view.updateView((BSCM)item);
        }
    }
}

