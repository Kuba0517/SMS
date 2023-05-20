package graphics;

import controller.BTSC;
import controller.VRDC;
import events.NetworkDevice;
import events.ViewUpdateListener;
import models.BTSM;
import models.ReceiverM;
import models.VRDM;

import javax.swing.*;
import java.awt.*;

public class BTSG extends JPanel implements ViewUpdateListener<BTSM> {
    private JLabel number;
    private JLabel smsTransfered;
    private JLabel pendingMessages;
    private NetworkDevice networkDevice;

    public void init(NetworkDevice inter){
        this.networkDevice = inter;
        initializeComponents();
    }

    public BTSG() {
    }

    private void initializeComponents() {
        if (networkDevice != null) {
            setLayout(new GridLayout(0, 1));
            number = new JLabel("Numer: " + networkDevice.getNumber());
            smsTransfered = new JLabel("Ilosc smsow: " + networkDevice.smsTransfered() );
            pendingMessages = new JLabel("Pending Messages: " + networkDevice.getPendingMessage());

            add(new JLabel("BTS"));
            add(number);
            add(smsTransfered);
            add(pendingMessages);
            revalidate();
            repaint();
        }
    }
    @Override
    public void updateView(BTSM item) {
        this.removeAll();
        BTSC controller = new BTSC(item);
        this.init(controller);
        revalidate();
        repaint();
    }

}