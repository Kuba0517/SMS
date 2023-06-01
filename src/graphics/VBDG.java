package graphics;

import events.VBD;

import javax.swing.*;
import java.awt.*;

public class VBDG extends JPanel{
    private JLabel statusLable;
    private JComboBox status;
    private JSlider frequency;
    private JLabel frequencyLabel;
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

            frequencyLabel = new JLabel("Frequency");
            frequency = new JSlider(JSlider.HORIZONTAL, 1, 10, inter.getFrequency());
            stopButton = new JButton("STOP");
            statusLable = new JLabel("Status");
            status = new JComboBox<>(new String[]{inter.getStatus(), inter.getStatus().equals("ACTIVE") ? "WAITING" : "ACTIVE"});
            deviceNumberField = new JTextField("Device Number: " + inter.getDeviceNumber());
            deviceNumberField.setEditable(false);


            status.addActionListener(e -> inter.setStatus((String) status.getSelectedItem()));
            stopButton.addActionListener(e -> inter.stop());
            frequency.addChangeListener(e -> inter.setFrequency(frequency.getValue()));

            add(statusLable);
            add(status);
            add(frequencyLabel);
            add(frequency);
            add(deviceNumberField);
            add(stopButton);
        }
    }

}
