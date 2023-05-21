package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import events.MessageReceiver;
import events.MessageReceiverBTS;
import events.ViewUpdateListener;

public class BTSM implements Runnable {
    private final Object listenersLock = new Object();
    private Thread thread;
    private static int number;
    private int thisNumber;
    private int transferedMessages;
    private List<ViewUpdateListener<BTSM>> listeners;
    private Queue<Message> messages;
    private MessageReceiverBTS messageReceiver;

    public BTSM(){
        this.messages = new ConcurrentLinkedQueue<>();
        this.listeners = new ArrayList<>();
        thisNumber = ++number;
    }

    public void incrementMessagesTransfered(){
        transferedMessages++;
        fireViewUpdate();
    }
    public void setMessageReceiver(MessageReceiverBTS messageReceiver) {
        this.messageReceiver = messageReceiver;
    }
    public String getNumber() {
        return Integer.toString(thisNumber);
    }


    public void addMessage(Message message) {
        messages.add(message);
        fireViewUpdate();
    }

    public void addViewUpdateListener(ViewUpdateListener<BTSM> listener) {
        synchronized (listenersLock) {
            listeners.add(listener);
        }
    }
    public Message retrieveMessage() {
        return messages.poll();
    };

    public String getTransferedMessage(){ return Integer.toString(transferedMessages);};
    public int getPendingMessage() {
        return messages.size();
    };

    @Override
    public void run() {
        while(true) {
            Message message = retrieveMessage();
            if(message != null) {
                messageReceiver.receiveMessageBTS(message, getNumber());
                incrementMessagesTransfered();
            } else {
                try {
                    Thread.sleep(3000); // Czekamy sekundę, jeżeli nie ma wiadomości do przesłania
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "BTSM Thread "+ thisNumber);
            thread.start();
        }
    }

    private void transmitMessage(Message message) {
        // Logika przesyłania wiadomości
    }

    private void fireViewUpdate() {
        List<ViewUpdateListener<BTSM>> copyListeners;
        synchronized (listenersLock) {
            copyListeners = new ArrayList<>(listeners);
        }
        for(ViewUpdateListener<BTSM> listener : copyListeners){
            listener.updateView(this);
        }
    }
}
