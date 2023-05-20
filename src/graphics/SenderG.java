package graphics;

import controller.VBDC;
import events.AddButtonListenerMessage;
import events.ViewUpdateListener;
import models.Message;
import models.SenderM;
import models.VBDM;

import javax.swing.*;
import java.awt.*;

public class SenderG extends JPanel implements ViewUpdateListener<SenderM> {
    private JButton addSenderButton;
    private JPanel devicePanel;
    private JScrollPane scrollPane;
    private AddButtonListenerMessage addButtonListener;

    public SenderG(AddButtonListenerMessage<Message> addButtonListener) {
        this.addButtonListener = addButtonListener;
        setLayout(new BorderLayout());

        addSenderButton = new JButton("Add");
        devicePanel = new JPanel();
        devicePanel.setLayout(new BoxLayout(devicePanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(devicePanel);

        add(addSenderButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        addSenderButton.addActionListener(e -> {
            String message = JOptionPane.showInputDialog(this, "Enter the message");
            Message messageObj = new Message(message);
            if (messageObj.getContent() != null) {
                addButtonListener.add(messageObj);
                revalidate();
                repaint();
            }
        });
    }

    @Override
    public void updateView(SenderM item) {
        devicePanel.removeAll();
        for(VBDM vbdm : item.getVBDList()) {
            System.out.println("hello");
            VBDC controller = new VBDC(vbdm);
            VBDG vbdg = new VBDG();
            vbdg.init(controller);
            devicePanel.add(vbdg);
        }
        devicePanel.revalidate();
        devicePanel.repaint();
    }
}