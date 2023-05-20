package graphics;

import javax.swing.*;
import java.awt.*;

public class MainWindowG extends JFrame {
    private JPanel sender;
    private JPanel receiver;
    private JPanel network;

    public MainWindowG(SenderG senderG, ReceiverG receiverG, NetworkG networkG) {
        super("SMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1080, 500));

        this.sender = senderG;
        this.receiver = receiverG;
        this.network = networkG;

        add(sender, BorderLayout.WEST);
        add(receiver, BorderLayout.EAST);
        add(network, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
}
