package controller;

import events.MessageListener;
import models.BTSM;
import models.Message;

public class BTSC implements MessageListener {
    private BTSM model;

    public BTSC(BTSM model) {
        this.model = model;
    }

    @Override
    public void addMessage(String content) {
        Message message = new Message(content);
        model.addMessage(message);
    }
}
