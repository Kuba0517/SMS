package models;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BTSM implements Runnable {
    private String id; // unikalny identyfikator BTS
    private Queue<Message> messages; // kolejka przechowująca wiadomości do przesłania

    public BTSM(String id) {
        this.id = id;
        this.messages = new ConcurrentLinkedQueue<>();
    }

    public String getId() {
        return id;
    }

    // Dodanie wiadomości do kolejki
    public void addMessage(Message message) {
        messages.add(message);
    }

    // Pobranie wiadomości z kolejki
    public Message retrieveMessage() {
        return messages.poll();
    }

    // Pobranie ilości oczekujących wiadomości
    public int getPendingMessagesCount() {
        return messages.size();
    }

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
}
