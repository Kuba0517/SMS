package graphics;

import controller.BSCC;
import events.NetworkDeviceInter;
import events.ViewUpdateListener;
import models.BSCM;

import javax.swing.*;
import java.awt.*;

public class BSCG extends JPanel implements ViewUpdateListener<BSCM> {
    private JLabel number;
    private JLabel smsTransfered;
    private JLabel pendingMessages;
    private NetworkDeviceInter networkDeviceInter;

    public void init(NetworkDeviceInter inter){
        this.networkDeviceInter = inter;
        initializeComponents();
    }

    public BSCG() {
    }

    private void initializeComponents() {
        if (networkDeviceInter != null) {
            setLayout(new GridLayout(0, 1));
            number = new JLabel("numer: " + networkDeviceInter.getNumber());
            smsTransfered = new JLabel("Przetworzone: " + networkDeviceInter.getSmsTransfered() );
            pendingMessages = new JLabel("Oczekujące sms: " + networkDeviceInter.getPendingMessage());

            add(new JLabel("BSC"));
            add(number);
            add(smsTransfered);
            add(pendingMessages);
        }
    }

    @Override
    public void updateView(BSCM item) {
        BSCC controller = new BSCC(item);
            smsTransfered.setText("Przetworzone: " + controller.getSmsTransfered());
            pendingMessages.setText("Oczekujące sms: " + controller.getPendingMessage());
    }

}
