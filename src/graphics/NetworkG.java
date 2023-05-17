package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;

public class NetworkG extends JPanel {
    private LinkedBlockingQueue<String> queue;
    private JButton addButton;
    private JButton removeButton;
    private JScrollPane scrollPane;

    public NetworkG() {
        queue = new LinkedBlockingQueue<>();

        setLayout(new BorderLayout());

        addButton = new JButton("Add BSC");
        removeButton = new JButton("Remove BSC");
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

