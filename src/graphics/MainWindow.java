package graphics;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private SenderG sender;
    private JPanel receiver;
    private JPanel network;

    public MainWindow(SenderG senderG) {
        super("SMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1080, 500));

        this.sender = senderG;
        receiver = new ReceiverG();
        network = new NetworkG();

        add(sender, BorderLayout.WEST);
        add(receiver, BorderLayout.EAST);
        add(network, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
}
