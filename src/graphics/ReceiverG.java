package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import controller.VRDC;
import models.VRDM;

public class ReceiverG extends JPanel {
    private JButton addButton;
    private JPanel devicePanel;
    private JScrollPane scrollPane;
    private ArrayList<VRDG> devices;
    private ArrayList<VRDC> controllers;

    public ReceiverG() {
        setLayout(new BorderLayout());

        devices = new ArrayList<>();
        addButton = new JButton("Add");
        devicePanel = new JPanel();
        devicePanel.setLayout(new BoxLayout(devicePanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(devicePanel);

        add(addButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            VRDM model = new VRDM();
            VRDC controller = new VRDC(model);
            VRDG graphics = new VRDG(devices.size(), controller, controller);
            devices.add(graphics);
            devicePanel.add(graphics);
            revalidate();
            repaint();
        });
    }
}

