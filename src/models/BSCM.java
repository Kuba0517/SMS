package models;

import events.MessageSender;
import events.MessageSenderBSC;
import events.Senders;

import java.util.Random;

public class BSCM extends NetworkDevice<BSCM>{
    private static final Random RANDOM = new Random();
    private final Object lock = new Object();
    private MessageSenderBSC messageSender;
    private int numberInOrder;


    public BSCM() {
        super();
    }


    @Override
    public void run() {
        while (true) {
            Message message;
            synchronized (lock) {
                while (messages.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                message = getMessagePeek();
            }
            if (message != null) {
                try {
                    Thread.sleep((RANDOM.nextInt(11) + 5)* 1000);
                    messageSender.sendMessageBSC(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public synchronized void addMessage(Message message) {
        synchronized (lock) {
            messages.add(message);
            lock.notify();
        }
        fireViewUpdate();
    }
    @Override
    public void setMessageSender(Senders messageSender) {
        this.messageSender = (MessageSenderBSC) messageSender;
    }

    public void setNumberInOrder(int numberInOrder){
        this.numberInOrder = numberInOrder;
    }

    public int getNumberInOrder(){
        return numberInOrder;
    }
}


