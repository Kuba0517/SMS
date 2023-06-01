package graphics;

import javax.swing.*;

import controller.VBDC;
import events.VRD;
import events.ViewUpdateListener;
import models.SenderM;
import models.VBDM;
import models.VRDM;

import java.awt.*;

public class VRDG extends JPanel implements ViewUpdateListener<VRDM> {
    private JButton stopButton;
    private JLabel messagesCount;
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
        messagesCount = new JLabel("Messages received: 0");
        resetCheckBox = new JCheckBox("Reset counter every 10s");
        resetCheckBox.setSelected(inter.getTick());
        resetCheckBox.addActionListener(e -> {
            inter.setTick();
        });

        add(stopButton);
        add(messagesCount);
        add(resetCheckBox);
    }

    @Override
    public void updateView(VRDM item) {
        messagesCount.setText("Messages received: " + inter.getNumberOfMessages());
        resetCheckBox.setSelected(inter.getTick());
        revalidate();
        repaint();
    }

}
