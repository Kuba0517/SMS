package graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReceiverG extends JPanel {
    private JButton addButton;
    private JPanel devicePanel;
    private JScrollPane scrollPane;
    private ArrayList<VRDG> devices;

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
            VRDG device = new VRDG(devices.size());
            devices.add(device);
            devicePanel.add(device);
            revalidate();
            repaint();
        });
    }
}

