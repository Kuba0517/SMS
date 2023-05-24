package controller;

import events.NetworkDeviceInter;
import events.ViewUpdateListener;
import models.BTSM;

public class BTSC implements ViewUpdateListener<BTSM>, NetworkDeviceInter {
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
    public String getSmsTransfered() {
        return Integer.toString(model.getTransferedSms());
    }

    @Override
    public String getPendingMessage() {
        return Integer.toString(model.getPendingMessage());
    }

    public void updateView(BTSM item) {
        if(view != null){
            view.updateView(item);
        }
    }
}
