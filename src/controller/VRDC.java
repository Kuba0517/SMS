package controller;

import events.GetReceipents;
import events.VRD;
import events.ViewUpdateListener;
import models.SenderM;
import models.VRDM;

public class VRDC implements VRD, ViewUpdateListener<VRDM> {
    private VRDM model;
    private ViewUpdateListener<VRDM> view;

    public VRDC(VRDM model) {
        this.model = model;
        model.setViewUpdateListener(this);
    }

    @Override
    public void setTick() {
        model.setTimedDelayed();
    }

    @Override
    public boolean getTick() {
        return model.getTimeDelayed();
    }

    @Override
    public void stop() {
        model.stop();
    }

    @Override
    public void cont() {

    }

    @Override
    public void updateView(VRDM item) {
        System.out.println("Aktualizacjjjjjajajaja");
        if(view != null){
            view.updateView(item);
            System.out.println("Aktualizacja widoku");
        }
    }

    public void setView(ViewUpdateListener<VRDM> view) {
        this.view = view;
        model.setViewUpdateListener(view);
    }

    @Override
    public String getNumberOfMessages() {
        return Integer.toString(model.getNumberOfMessages());
    }
}