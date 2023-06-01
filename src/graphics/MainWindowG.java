package graphics;


import controller.NetworkC;
import events.Closing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowG extends JFrame {
    private JPanel sender;
    private JPanel receiver;
    private JPanel network;
    private Closing closing;

    public MainWindowG() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1080, 500));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closing.sendAllMessages();
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    public void setSender(SenderG sender) {
        this.sender = sender;
        add(sender, BorderLayout.WEST);
    }

    public void setReceiver(ReceiverG receiver) {
        this.receiver = receiver;
        add(receiver, BorderLayout.EAST);
    }

    public void setNetwork(NetworkG network) {
        this.network = network;
        add(network, BorderLayout.CENTER);
    }

    public void setClosing(NetworkC closing) {
        this.closing = closing;
    }
}
