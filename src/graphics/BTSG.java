package graphics;

import events.MessageListener;
import javax.swing.*;
import java.awt.*;

public class BTSG extends JPanel {
    private JLabel number;
    private JLabel smsTransfered;
    private JLabel pendingMessagesLabel;
    private MessageListener messageListener;

    public BTSG() {

        setLayout(new GridLayout(0, 1));

        number = new JLabel("Losowy unikalny numer");
        smsTransfered = new JLabel("Ilosc smsow");
        pendingMessagesLabel = new JLabel("Pending Messages: 0");


        add(number);
        add(smsTransfered);
        add(pendingMessagesLabel);
    }

    public void updatePendingMessages(int count) {
        pendingMessagesLabel.setText("Pending Messages: " + count);
    }
}

