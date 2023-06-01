package models;

import events.MessageSender;
import events.Senders;
import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class NetworkDevice<T extends NetworkDevice<T>> implements Runnable {
    protected final Object listenersLock = new Object();
    private static int number;
    private int thisNumber;
    private int smsTransfered;
    protected Queue<Message> messages;
    private int smsPending;
    private List<ViewUpdateListener<T>> listeners;
    private Thread thread;

    public NetworkDevice() {
        this.messages = new LinkedList<>();
        this.smsTransfered = 0;
        this.smsPending = 0;
        this.listeners = new ArrayList<>();
        thread = new Thread(this);
        thisNumber = ++number;
    }

    public void addViewUpdateListener(ViewUpdateListener<T> listener) {
        synchronized (listenersLock) {
            listeners.add(listener);
        }
    }

    public String getNumber() {
        return Integer.toString(thisNumber);
    }

    public void incrementMessagesTransfered() {
        smsTransfered++;
        fireViewUpdate();
    }

    public int getTransferedSms() {
        return smsTransfered;
    }

    public abstract void addMessage(Message message);

    public abstract void setMessageSender(Senders sender);

    public abstract void run();


    public synchronized Message getMessagePeek() {
        return messages.peek();
    }

    public synchronized Message getMessagePoll() {
        incrementMessagesTransfered();
        return messages.poll();
    }

    public void startThread(){
        thread.start();
    }
    public Thread getThread(){
        return thread;
    }
    

    public int getPendingMessage() {
        return messages.size();
    }


    protected void fireViewUpdate() {
        List<ViewUpdateListener<T>> copyListeners;
        synchronized (listenersLock) {
            copyListeners = new ArrayList<>(listeners);
        }
        for(ViewUpdateListener<T> listener : copyListeners){
            listener.updateView((T) this);
        }
    }


}

