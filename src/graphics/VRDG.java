package graphics;

import javax.swing.*;
import events.VRD;

import java.awt.*;

public class VRDG extends JPanel {
    private JButton stopButton;
    private JLabel messageCountLabel;
    private JCheckBox resetCheckBox;
    private VRD inter;

    public void init(VRD inter) {
        this.inter = inter;
        InitializeComponents();
    }

    public VRDG() {
    }

    private void InitializeComponents() {
        setLayout(new GridLayout(0, 1));

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            inter.stop();
        });
        messageCountLabel = new JLabel("Messages received: 0");
        resetCheckBox = new JCheckBox("Reset counter every 10s");
        resetCheckBox.addActionListener(e -> {
            inter.setTick();
        });

        add(stopButton);
        add(messageCountLabel);
        add(resetCheckBox);
    }

}
