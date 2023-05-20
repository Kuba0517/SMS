package controller;

import events.VRD;
import models.VRDM;

public class VRDC implements VRD {
    private VRDM model;

    public VRDC(VRDM model) {
        this.model = model;
    }

    @Override
    public void setTick() {
        model.setTimedDelete();
        System.out.println(model.getTimeDelete());
    }

    @Override
    public void stop() {
        model.stop();
        System.out.println("The VRD has stopped");
    }

    @Override
    public void start() {

    }

    @Override
    public int getNumberOfMessages() {
        return model.getNumberOfMessages();
    }
}