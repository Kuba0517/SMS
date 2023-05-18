package graphics;

import events.BTSConnectionListener;

import javax.swing.*;
import java.awt.*;

public class BSCG extends JPanel {
    private JLabel number;
    private JLabel smsTransfered;
    private JLabel pendingMessages;

    public BSCG() {

        setLayout(new GridLayout(0,1));

        number = new JLabel("Losowy unikalny numer");
        smsTransfered = new JLabel("Ilosc smsow");
        pendingMessages = new JLabel("Pending Messages: 0");


        add(number);
        add(smsTransfered);
        add(pendingMessages);
    }

    public void updatePendingMessages(int count) {
        pendingMessages.setText("Pending Messages: " + count);
    }
}
