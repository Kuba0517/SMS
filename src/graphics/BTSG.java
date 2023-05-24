package graphics;

import controller.BTSC;
import events.NetworkDeviceInter;
import events.ViewUpdateListener;
import models.BTSM;

import javax.swing.*;
import java.awt.*;

public class BTSG extends JPanel implements ViewUpdateListener<BTSM> {
    private JLabel number;
    private JLabel smsTransfered;
    private JLabel pendingMessages;
    private NetworkDeviceInter networkDeviceInter;

    public void init(NetworkDeviceInter inter){
        this.networkDeviceInter = inter;
        initializeComponents();
    }

    public BTSG() {
    }

    private void initializeComponents() {
        if (networkDeviceInter != null) {
            setLayout(new GridLayout(0, 1));
            number = new JLabel("Numer: " + networkDeviceInter.getNumber());
            smsTransfered = new JLabel("Przetworzone: " + networkDeviceInter.getSmsTransfered() );
            pendingMessages = new JLabel("Pending Messages: " + networkDeviceInter.getPendingMessage());

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
        BTSC controller = new BTSC(item);
            smsTransfered.setText("Przetworzone: " + controller.getSmsTransfered());
            pendingMessages.setText("Pending Messages: " + controller.getPendingMessage());
    }

}