package models;

import java.util.LinkedList;
import java.util.Queue;

public class BSCM implements Runnable {
    private Queue<String> messageQueue;
    private boolean isActive;
    private int timeOut;

    public BSCM(int timeOut) {
        this.messageQueue = new LinkedList<>();
        this.isActive = true;
        this.timeOut = timeOut;
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
                // delay the processing of the message
                try {
                    Thread.sleep(timeOut * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted, Failed to complete operation");
                }
                // TODO: implement logic to pass the message to the next layer
            }
        }
    }
}
