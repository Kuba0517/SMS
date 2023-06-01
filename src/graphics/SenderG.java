package graphics;

import controller.VBDC;
import events.AddButtonListenerMessage;
import events.ViewUpdateListener;
import models.Message;
import models.NetworkDevice;
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
            JDialog dialog = new JDialog();
            dialog.setModal(true);
            dialog.setLayout(new FlowLayout());

            JTextField textField = new JTextField(20);
            JButton okButton = new JButton("SEND");

            okButton.addActionListener(e1 -> {
                String message = textField.getText();
                Message messageobj = new Message(message);
                if (messageobj.getContent() != null) {
                    addButtonListener.add(messageobj);
                    revalidate();
                    repaint();
                }
                dialog.dispose();
            });

            dialog.add(new JLabel("Message:"));
            dialog.add(textField);
            dialog.add(okButton);

            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
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