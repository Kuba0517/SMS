package graphics;

import events.AddButtonListener;
import events.ViewUpdateListener;
import models.Message;
import models.VBDM;

import javax.swing.*;
import java.awt.*;

public class SenderG extends JPanel implements ViewUpdateListener<VBDM> {
    private JButton addSenderButton;
    private JPanel devicePanel;
    private JScrollPane scrollPane;
    private AddButtonListener addButtonListener;

    public SenderG(AddButtonListener addButtonListener) {
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
                addButtonListener.addItem(messageObj);
                revalidate();
                repaint();
            }
        });
    }

    @Override
    public void updateView(VBDM vbdm) {
        JLabel label = new JLabel("New VBDM added: " + vbdm.getMessage().getContent());
        devicePanel.add(label);
        revalidate();
        repaint();
    }
}