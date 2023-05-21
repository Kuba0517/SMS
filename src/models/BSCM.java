package models;

import events.MessageReceiverBSC;
import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;

public class BSCM extends NetworkDevice implements Runnable{


    public BSCM() {
        this.messages = new ConcurrentLinkedQueue<>();
        this.smsTransfered = 0;
        this.smsPending = 0;
        this.listeners = new ArrayList<>();
        thisNumber = ++number;
    }


    public void addViewUpdateListener(ViewUpdateListener<BSCM> listener){
        listeners.add(listener);
    }
    public String getNumber() {
        return Integer.toString(thisNumber);
    }

    public String getSmsTransfered() {
        return Integer.toString(smsTransfered);
    }

    public void incrementMessagesTransfered(){
        smsTransfered++;
        fireViewUpdate();
    }

    public void addMessage(Message message) {
        messages.add(message);
        fireViewUpdate();
    }
    public void setMessageReceiver(MessageReceiverBSC messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    public Message retrieveMessage() {
        return messages.poll();
    }

    public int getPendingMessage() {
        return messages.size();
    }

    public void setSmsPending(int smsPending) {
        this.smsPending = smsPending;
    }

    private void fireViewUpdate() {
        List<ViewUpdateListener<BSCM>> copyListeners;
        synchronized (listenersLock) {
            copyListeners = new ArrayList<>(listeners);
        }
        for(ViewUpdateListener<BSCM> listener : copyListeners){
            listener.updateView(this);
        }
    }

    @Override
    public void run() {
        while(true) {
            Message message = retrieveMessage();
            if(message != null) {
                messageReceiver.receiveMessageBSC(message, getNumber());
                incrementMessagesTransfered();
            } else {
                try {
                    Thread.sleep((RANDOM.nextInt(10) + 5) * 1000); // Czekamy sekundę, jeżeli nie ma wiadomości do przesłania
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public void Start(){
        thread = new Thread(this);
        thread.start();
    }
}
