package models;

import java.util.LinkedList;

import events.Device;
import events.MessageListener;
import events.RemoveListener;
import events.ViewUpdateListener;
import java.util.Queue;


public class VRDM implements Runnable, Device, MessageListener {
    private Queue<String> messageQueue;
    private boolean isActive;
    private static int counter = 1;
    private boolean timedDelayed;
    private RemoveListener removeListener;
    private ViewUpdateListener<VRDM> listener;
    private Thread thread;
    private int telephoneNumber;

    public VRDM(RemoveListener removeListener) {
        this.messageQueue = new LinkedList<>();
        this.isActive = true;
        this.timedDelayed = false;
        this.removeListener = removeListener;
        this.telephoneNumber = counter++;
        this.thread = new Thread(this);
        thread.start();
    }



    public void setActive(boolean active) {
        isActive = active;
    }


    public void setTimedDelayed() {
        synchronized(this) {
            this.timedDelayed = !this.timedDelayed;
            if(this.timedDelayed) {
                this.notify();
            }
        }
    }


    public boolean getTimeDelayed() {
        return timedDelayed;
    }

    public int getNumberOfMessages(){
        return messageQueue.size();
    }


    @Override
    public void messageTo(Message message) {
        synchronized (this) {
            messageQueue.add(message.getContent());
            fireViewUpdate();
        }
    }

    private void clearMessageQueue() {
        synchronized (this) {
            messageQueue.clear();
            fireViewUpdate();
        }
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        while (isActive) {
            synchronized(this) {
                while (!timedDelayed) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
            try {
                Thread.sleep(10000);
                if (timedDelayed) {
                    clearMessageQueue();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }



    @Override
    public void stop() {
        this.isActive = false;
        if (thread != null) {
            thread.interrupt();  // Przerwij wątek
        }
        removeListener.remove(this);
    }

    @Override
    public void cont() {

    }

    public void setViewUpdateListener(ViewUpdateListener<VRDM> listener) { // Zmienione na set zamiast add
        this.listener = listener;
    }

    private void fireViewUpdate(){
        if(listener != null){
            listener.updateView(this);
        }
    }

    public int getNumber(){
        return telephoneNumber;
    }

}

