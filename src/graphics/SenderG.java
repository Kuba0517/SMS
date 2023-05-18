package graphics;

import controller.VBDC;
import models.VBDM;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SenderG extends JPanel {
    private JButton addSenderButton;
    private JPanel devicePanel;
    private JScrollPane scrollPane;
    private ArrayList<VBDG> devices;
    private ArrayList<VBDC> controllers;

    public SenderG() {
        setLayout(new BorderLayout());

        devices = new ArrayList<>();
        controllers = new ArrayList<>();
        addSenderButton = new JButton("Add");
        devicePanel = new JPanel();
        devicePanel.setLayout(new BoxLayout(devicePanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(devicePanel);

        add(addSenderButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        addSenderButton.addActionListener(e -> {
            String message = JOptionPane.showInputDialog(this, "Enter the message");
            if (message != null) {
                VBDM model = new VBDM(message,1); // tworzymy nowy model
                VBDC controller = new VBDC(model); // tworzymy nowy kontroler z tym modelem
                VBDG device = new VBDG(controller, controller, controller, "Hello", 10); // przekazujemy kontroler jako ActivityListener i FrequencyListener
                devices.add(device);
                controllers.add(controller); // zapisujemy referencję do kontrolera
                devicePanel.add(device);
                revalidate();
                repaint();
            }
        });
    }
}
