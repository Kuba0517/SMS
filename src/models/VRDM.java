package models;

import java.util.LinkedList;
import java.util.Queue;

public class VRDM implements Runnable {
    private Queue<String> messageQueue;
    private boolean isActive;

    public VRDM() {
        this.messageQueue = new LinkedList<>();
        this.isActive = true;
    }

    public void receiveMessage(String message) {
        synchronized (this) {
            messageQueue.add(message);
            notify();
        }
    }

    public void stop() {
        this.isActive = false;
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

