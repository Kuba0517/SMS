package graphics;

import events.VBD;

import javax.swing.*;
import java.awt.*;

public class VBDG extends JPanel{
    private JComboBox statusLabel;
    private JSlider frequencyLabel;
    private JTextField deviceNumberField;
    private JButton stopButton;
    private VBD inter;

    public void init(VBD inter){
        this.inter = inter;
        initializeComponents();
    }
    public VBDG() {
    }
    private void initializeComponents() {
        if (inter != null) {
            setLayout(new GridLayout(0, 1));
            frequencyLabel = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
            stopButton = new JButton("STOP");
            statusLabel = new JComboBox<>(new String[]{"ACTIVE", "WAITING"});
            deviceNumberField = new JTextField("Device Number: " + inter.getDeviceNumber());
            deviceNumberField.setEditable(false);


            statusLabel.addActionListener(e -> inter.setStatus((String) statusLabel.getSelectedItem()));
            stopButton.addActionListener(e -> inter.stop());
            frequencyLabel.addChangeListener(e -> inter.setFrequency(frequencyLabel.getValue()));

            add(statusLabel);
            add(frequencyLabel);
            add(deviceNumberField);
            add(stopButton);
        }
    }

}
