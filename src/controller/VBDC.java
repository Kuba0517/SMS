package controller;

import events.ActivityListener;
import events.FrequencyListener;
import graphics.VBDG;
import models.VBDM;

public class VBDC implements ActivityListener, FrequencyListener {
    private VBDM model;

    public VBDC(VBDM model) {
        this.model = model;
    }

    @Override
    public void setFrequency(int frequency) {
        model.setFrequency(frequency);
        System.out.println("Zmiana czestotliwości na " + frequency);
    }

    @Override
    public void stop() {
        model.setActive(false);
        System.out.println("Stop");
    }

    @Override
    public void statusChanged(String newStatus) {
        System.out.println("Status changed to: " + newStatus);
    }

    @Override
    public void frequencyChanged(int newFrequency) {

    }
}

