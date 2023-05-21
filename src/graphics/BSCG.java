package graphics;

import controller.BSCC;
import controller.VRDC;
import events.NetworkDevice;
import events.ViewUpdateListener;
import models.BSCM;
import models.ReceiverM;
import models.VRDM;

import javax.swing.*;
import java.awt.*;

public class BSCG extends JPanel implements ViewUpdateListener<BSCM> {
    private JLabel number;
    private JLabel smsTransfered;
    private JLabel pendingMessages;
    private NetworkDevice networkDevice;

    public void init(NetworkDevice inter){
        this.networkDevice = inter;
        initializeComponents();
    }

    public BSCG() {
    }

    private void initializeComponents() {
        if (networkDevice != null) {
            setLayout(new GridLayout(0, 1));
            number = new JLabel("numer: " + networkDevice.getNumber());
            smsTransfered = new JLabel("Ilosc smsow: " + networkDevice.smsTransfered() );
            pendingMessages = new JLabel("Oczekujące sms: " + networkDevice.getPendingMessage());

            add(new JLabel("BSC"));
            add(number);
            add(smsTransfered);
            add(pendingMessages);
        }
    }

    @Override
    public void updateView(BSCM item) {
        this.removeAll();
        BSCC controller = new BSCC(item);
        this.init(controller);
        revalidate();
        repaint();
    }

}
