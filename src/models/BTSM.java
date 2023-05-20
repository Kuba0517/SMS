package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import events.ViewUpdateListener;

public class BTSM implements Runnable {
    private final Object listenersLock = new Object();
    private static int number;
    private int thisNumber;
    private int transferedMessages;
    private List<ViewUpdateListener<BTSM>> listeners;
    private Queue<Message> messages; // kolejka przechowująca wiadomości do przesłania

    public BTSM() {
        this.messages = new ConcurrentLinkedQueue<>();
        this.listeners = new ArrayList<>();
        thisNumber = ++number;
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
                transmitMessage(message);
            } else {
                try {
                    Thread.sleep(1000); // Czekamy sekundę, jeżeli nie ma wiadomości do przesłania
                } catch(InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
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
