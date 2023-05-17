package graphics;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                ()-> new MainWindow()
        );
    }

    private JPanel sender;
    private JPanel receiver;
    private JPanel network;

    public MainWindow() {
        super("SMS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1080, 500));

        sender = new SenderG();
        receiver = new ReceiverG();
        network = new NetworkG();

        add(sender, BorderLayout.WEST);
        add(receiver, BorderLayout.EAST);
        add(network, BorderLayout.CENTER);


        pack();
        setVisible(true);
    }
}
