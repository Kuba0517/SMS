package models;

import events.MessageSender;
import events.MessageSenderBSC;
import events.Senders;

import java.util.Random;

public class BSCM extends NetworkDevice<BSCM>{
    private static final Random RANDOM = new Random();
    private MessageSenderBSC messageSender;


    public BSCM() {
        super();
    }


    @Override
    public synchronized void addMessage(Message message) {
            messages.add(message);
        fireViewUpdate();
    }
    @Override
    public void setMessageSender(Senders messageSender) {
        this.messageSender = (MessageSenderBSC) messageSender;
    }

}


