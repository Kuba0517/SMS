package graphics;

import events.BTSConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

public class NetworkG extends JPanel {
    private LinkedBlockingQueue<String> queue;
    private JButton addButton;
    private JButton removeButton;
    private JScrollPane scrollPane;
    private BTSConnectionListener connectionListener;

    public NetworkG(BTSConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
        queue = new LinkedBlockingQueue<>();

        setLayout(new BorderLayout());

        addButton = new JButton("Add BSC");
        addButton.addActionListener(e -> {
            // Tutaj dodajemy logikę dodawania BSC
        });

        removeButton = new JButton("Remove BSC");
        removeButton.addActionListener(e -> {
            // Tutaj dodajemy logikę usuwania BSC
        });

        scrollPane = new JScrollPane();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void sendMessage(String message) {
        queue.offer(message);
    }

    public String receiveMessage() {
        return queue.poll();
    }
}
