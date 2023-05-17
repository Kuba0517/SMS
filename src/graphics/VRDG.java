package graphics;

import javax.swing.*;
import java.awt.*;

public class VRDG extends JPanel {
    private JButton stopButton;
    private JLabel messageCountLabel;
    private JCheckBox resetCheckBox;

    public VRDG(int deviceNumber) {
        setLayout(new GridLayout(0, 1));

        stopButton = new JButton("Stop");
        messageCountLabel = new JLabel("Messages received: 0");
        resetCheckBox = new JCheckBox("Reset counter every 10s");

        add(stopButton);
        add(messageCountLabel);
        add(resetCheckBox);
    }
}
