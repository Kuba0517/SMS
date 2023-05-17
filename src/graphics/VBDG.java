package graphics;

import events.ActivityListener;
import events.FrequencyListener;

import javax.swing.*;
import java.awt.*;

public class VBDG extends JPanel {
    private JSlider frequencySlider;
    private JButton stopButton;
    private JTextField deviceNumberField;
    private JComboBox<String> statusComboBox;
    private ActivityListener activityListener;
    private FrequencyListener frequencyListener;

    public VBDG(ActivityListener activityListener, FrequencyListener frequencyListener, String message, int deviceNumber) {
        this.activityListener = activityListener;
        this.frequencyListener = frequencyListener;
        setLayout(new GridLayout(0, 1));


        frequencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        frequencySlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int newFrequency = source.getValue();
                frequencyListener.setFrequency(newFrequency);
            }
        });

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            activityListener.stop();
        });

        deviceNumberField = new JTextField(String.valueOf(deviceNumber));
        deviceNumberField.setEditable(false);
        statusComboBox = new JComboBox<>(new String[] {"WAITING", "ACTIVE"});

        add(new JLabel("Frequency: "));
        add(frequencySlider);
        add(stopButton);
        add(new JLabel("Device Number: "));
        add(deviceNumberField);
        add(new JLabel("Status: "));
        add(statusComboBox);
    }

}

