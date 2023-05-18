package controller;

import events.TickListener;
import events.StopListener;
import models.VRDM;

public class VRDC implements StopListener, TickListener {
    private VRDM model;

    public VRDC(VRDM model) {
        this.model = model;
    }

    @Override
    public void tickChanged() {
        model.setTimedDelete();
        System.out.println(model.getTimeDelete());
    }

    @Override
    public void stop() {
        model.setActive(false);
        System.out.println("The VRD has stopped");
    }

}