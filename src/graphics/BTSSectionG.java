package graphics;

import controller.BTSC;
import events.MultiSection;
import events.NetworkDeviceInter;
import events.ViewUpdateListener;
import models.BTSM;
import models.BTSSectionModel;

import javax.swing.*;
import java.awt.*;

public class BTSSectionG extends JPanel implements ViewUpdateListener<BTSSectionModel> {
    private MultiSection btsSection;
    private JPanel btsPanel;


    public BTSSectionG() {
        setLayout(new BorderLayout());

        btsPanel = new JPanel();
        btsPanel.setLayout(new BoxLayout(btsPanel, BoxLayout.Y_AXIS));


        JScrollPane scrollPane = new JScrollPane(btsPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void init(MultiSection btsSection){
        this.btsSection = btsSection;
        initializeComponents();
    }

    private void initializeComponents() {
        for (Object btsm : btsSection.getItems()) {
            BTSC controller = new BTSC((BTSM)btsm);
            BTSG btsg = new BTSG();
            btsg.init(controller);
            controller.setView(btsg);
            btsPanel.add(btsg); // Zmienić this na btsPanel
        }
    }


    @Override
    public void updateView(BTSSectionModel item) {
        btsPanel.removeAll(); // Zmienić this na btsPanel
        initializeComponents();
        btsPanel.revalidate();
        btsPanel.repaint();
    }
}
