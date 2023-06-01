package models;

import events.MessageSender;
import events.MessageSenderBSC;
import events.Senders;

import java.util.Random;

public class BSCM extends NetworkDevice<BSCM>{
    private Random random = new Random();
    private final Object lock = new Object();
    private MessageSenderBSC messageSender;
    private int numberInOrder;


    public BSCM() {
        super();
        fireViewUpdate();
    }


    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Message message;
                synchronized (lock) {
                    while (messages.isEmpty()) {
                        lock.wait();
                    }
                    message = getMessagePeek();
                }
                if (message != null) {
                    Thread.sleep((random.nextInt(11) + 5) * 1000);
                    messageSender.sendMessageBSC(this);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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


