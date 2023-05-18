package graphics;

import controller.BSCC;
import models.BSCM;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.ArrayList;

public class NetworkG extends JPanel {
    private LinkedBlockingQueue<String> queue;
    private JPanel NetworkPanel;
    private JButton addButton;
    private JButton removeButton;
    private JScrollPane scrollPane;

    private ArrayList<BSCC> bscControllers = new ArrayList<>();

    public NetworkG() {
        queue = new LinkedBlockingQueue<>();

        NetworkPanel = new JPanel();
        NetworkPanel.setLayout(new BoxLayout(NetworkPanel, BoxLayout.X_AXIS));

        setLayout(new BorderLayout());

        addButton = new JButton("Add BSC");
        addButton.addActionListener(e -> {
            BSCM model = new BSCM();
            BSCG graphic = new BSCG();
            BSCC controller = new BSCC(model, graphic);
            bscControllers.add(controller);
            NetworkPanel.add(graphic);
            NetworkPanel.revalidate();
            NetworkPanel.repaint();
        });

        removeButton = new JButton("Remove BSC");
        removeButton.addActionListener(e -> {
            if (!bscControllers.isEmpty()) {
                BSCC controllerToRemove = bscControllers.remove(bscControllers.size() - 1);
                NetworkPanel.remove(controllerToRemove.getGraphic());
                NetworkPanel.revalidate();
                NetworkPanel.repaint();
                // Here you should add logic for deactivating BSC.
            }
        });

        scrollPane = new JScrollPane(NetworkPanel);
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
