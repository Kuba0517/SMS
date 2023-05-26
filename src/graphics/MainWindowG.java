package graphics;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowG extends JFrame {
    private JPanel sender;
    private JPanel receiver;
    private JPanel network;

    public MainWindowG(SenderG senderG, ReceiverG receiverG, NetworkG networkG) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1080, 500));

        this.sender = senderG;
        this.receiver = receiverG;
        this.network = networkG;


        add(sender, BorderLayout.WEST);
        add(receiver, BorderLayout.EAST);
        add(network, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Clooosing");
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

}
