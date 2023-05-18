package graphics;

import events.ActivityListener;
import events.FrequencyListener;
import events.StopListener;

import javax.swing.*;
import java.awt.*;

public class VBDG extends JPanel {
    private JSlider frequencySlider;
    private JButton stopButton;
    private JTextField deviceNumberField;
    private JComboBox<String> statusComboBox;
    private ActivityListener activityListener;
    private FrequencyListener frequencyListener;
    private StopListener stopListener;
    private String message;
    private int deviceNumber;

    public VBDG() {
        setLayout(new GridLayout(0, 1));

        frequencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        stopButton = new JButton("Stop");
        deviceNumberField = new JTextField();
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

    public void init(ActivityListener activityListener, FrequencyListener frequencyListener, StopListener stopListener, String message, int deviceNumber) {
        this.activityListener = activityListener;
        this.frequencyListener = frequencyListener;
        this.stopListener = stopListener;
        this.message = message;
        this.deviceNumber = deviceNumber;
        deviceNumberField.setText(String.valueOf(deviceNumber));

        frequencySlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int newFrequency = source.getValue();
                frequencyListener.setFrequency(newFrequency);
            }
        });

        stopButton.addActionListener(e -> {
            stopListener.stop();
        });

        statusComboBox.addActionListener(e -> {
            String status = (String) statusComboBox.getSelectedItem();
            activityListener.statusChanged(status);
        });
    }
}
