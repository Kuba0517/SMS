package graphics;

import controller.BTSC;
import controller.NetworkC;
import events.ViewUpdateListener;
import models.BTSM;
import models.MainWindowM;
import models.NetworkM;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindowG extends JFrame implements ViewUpdateListener<MainWindowM> {
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
        this.network = new JPanel();
        this.network.setLayout(new BoxLayout(this.network, BoxLayout.Y_AXIS));
        network.add(networkG);


        add(sender, BorderLayout.WEST);
        add(receiver, BorderLayout.EAST);
        add(network, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    @Override
    public void updateView(MainWindowM item) {
        this.network.removeAll();
        for (NetworkM networkM: item.getNetworks()) {
            NetworkC networkC = new NetworkC(networkM);
            NetworkG networkG = new NetworkG(networkC, networkC);
            network.add(networkG);
        }
        revalidate();
        repaint();
    }
}
