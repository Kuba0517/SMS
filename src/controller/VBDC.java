package controller;

import events.VBD;
import models.Message;
import models.VBDM;

public class VBDC implements VBD {
    private VBDM model;

    public VBDC(VBDM model) {
        this.model = model;
    }

    @Override
    public void setStatus(String newStatus) {
        model.setStatus(newStatus);
    }

    @Override
    public void setFrequency(int newFrequency) {
        model.setFrequency(newFrequency);
    }

    @Override
    public void setMessage(Message message) {
        model.setMessage(message);
    }

    @Override
    public void cont(){
        model.setStatus("ACTIVE");
    }
    @Override
    public void stop() {
        model.stop();
    }

    @Override
    public String getDeviceNumber() {
        return Integer.toString(model.getDeviceNumber());
    }

    @Override
    public int getFrequency() {
        return model.getFrequency();
    }

    @Override
    public String getStatus() {
        return model.getStatus();
    }
}
