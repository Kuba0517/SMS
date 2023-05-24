package models;

import events.MessageSender;
import events.MessageSenderBTS;
import events.Senders;

public class BTSM extends NetworkDevice<BTSM> {
    private MessageSenderBTS messageSender;

    public BTSM() {
        super();
    }


    public synchronized void addMessage(Message message) {
        messages.add(message);
        fireViewUpdate();
    }
    @Override
    public void setMessageSender(Senders messageSender) {
        this.messageSender = messageSender;
    }



}


