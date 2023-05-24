package models;

import events.MessageSender;
import events.Senders;
import events.ViewUpdateListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class NetworkDevice<T extends NetworkDevice<T>> {
    protected final Object listenersLock = new Object();
    private static int number;
    private int thisNumber;
    private int smsTransfered;
    protected Queue<Message> messages;
    private int smsPending;
    private List<ViewUpdateListener<T>> listeners;

    public NetworkDevice() {
        this.messages = new LinkedList<>();
        this.smsTransfered = 0;
        this.smsPending = 0;
        this.listeners = new ArrayList<>();
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


    public synchronized Message getMessage() {
        incrementMessagesTransfered();
        return messages.poll();
    }

    public synchronized void incrementPendingMessages() {
        smsPending++;
        fireViewUpdate();
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

