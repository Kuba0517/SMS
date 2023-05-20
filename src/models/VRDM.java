package models;

import java.util.LinkedList;

import events.Device;
import events.RemoveListener;
import events.ViewUpdateListener;
import java.util.Queue;

public class VRDM implements Runnable, Device {
    private Queue<String> messageQueue;
    private boolean isActive;
    private int numberOfMessages;
    private boolean timedDelete;
    private RemoveListener removeListener;

    public VRDM(RemoveListener removeListener) {
        this.messageQueue = new LinkedList<>();
        this.isActive = true;
        this.timedDelete = false;
        this.numberOfMessages = 0;
        this.removeListener = removeListener;
    }


    public void setActive(boolean active) {
        isActive = active;
    }

    public void setNumberOfMessages(int numberOfMessages) {
        this.numberOfMessages = numberOfMessages;
    }

    public void setTimedDelete() {
        this.timedDelete = !this.timedDelete;
    }

    public boolean getTimeDelete(){
        return timedDelete;
    }
    public int getNumberOfMessages(){
        return numberOfMessages;
    }

    public void receiveMessage(String message) {
        synchronized (this) {
            messageQueue.add(message);
            notify();
        }
    }

    @Override
    public void stop() {
        this.isActive = false;
        removeListener.remove((Device)this);
    }

    @Override
    public void start() {

    }

    @Override
    public void run() {
        while (isActive) {
            synchronized (this) {
                while (messageQueue.isEmpty()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Thread was interrupted, Failed to complete operation");
                    }
                }
                // process received message
                String receivedMessage = messageQueue.poll();
                // TODO: implement logic to process the received message
            }
        }
    }
}

