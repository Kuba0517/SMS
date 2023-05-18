package graphics;

import javax.swing.*;
import java.awt.*;
import events.ActivityListener;
import events.TickListener;
import events.StopListener;

public class VRDG extends JPanel {
    private JButton stopButton;
    private JLabel messageCountLabel;
    private JCheckBox resetCheckBox;
    private StopListener stopListener;
    private TickListener tickListener;

    public VRDG(int deviceNumber, StopListener stopListener, TickListener tickListener) {
        this.stopListener = stopListener;
        this.tickListener = tickListener;
        setLayout(new GridLayout(0, 1));

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e-> {
            stopListener.stop();
        });
        messageCountLabel = new JLabel("Messages received: 0");
        resetCheckBox = new JCheckBox("Reset counter every 10s");
        resetCheckBox.addActionListener(e->{
            tickListener.tickChanged();
        });


        add(stopButton);
        add(messageCountLabel);
        add(resetCheckBox);
    }
}
