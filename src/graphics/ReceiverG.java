package graphics;

import javax.swing.*;
import java.awt.*;

import controller.VRDC;
import events.AddButtonListener;
import events.ViewUpdateListener;
import models.Message;
import models.ReceiverM;
import models.VRDM;


public class ReceiverG extends JPanel implements ViewUpdateListener<ReceiverM> {
    private JButton addButton;
    private JPanel devicePanel;
    private JScrollPane scrollPane;
    private AddButtonListener addButtonListener;

    public ReceiverG(AddButtonListener addButtonListener) {
        this.addButtonListener = addButtonListener;
        setLayout(new BorderLayout());

        addButton = new JButton("Add");

        devicePanel = new JPanel();
        devicePanel.setLayout(new BoxLayout(devicePanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(devicePanel);

        add(addButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            addButtonListener.add();
            revalidate();
            repaint();
        });
    }

    @Override
    public void updateView(ReceiverM item) {
        devicePanel.removeAll();
        for(VRDM vrdm : item.getVRDList()) {
            VRDC controller = new VRDC(vrdm);
            VRDG graphic = new VRDG();
            graphic.init(controller);
            devicePanel.add(graphic);
        }
        revalidate();
        repaint();
    }
}

