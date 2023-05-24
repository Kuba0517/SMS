package graphics;

import controller.BSCC;
import controller.BSCSectionController;
import controller.BTSC;
import controller.BTSSectionController;
import events.AddButtonListener;
import events.NetworkDeviceInter;
import events.RemoveButtonListener;
import events.ViewUpdateListener;
import models.*;

import javax.swing.*;
import java.awt.*;

public class NetworkG extends JPanel implements ViewUpdateListener<NetworkM> {
    private JPanel NetworkPanel;
    private JButton addButton;
    private JButton removeButton;
    private JScrollPane scrollPane;
    private AddButtonListener addButtonListener;
    private RemoveButtonListener removeButtonListener;


    public NetworkG(AddButtonListener addButtonListener, RemoveButtonListener removeButtonListener) {
        this.addButtonListener = addButtonListener;
        this.removeButtonListener = removeButtonListener;

        setLayout(new BorderLayout());

        NetworkPanel = new JPanel();
        NetworkPanel.setLayout(new BoxLayout(NetworkPanel, BoxLayout.X_AXIS));

        NetworkPanel.add(Box.createHorizontalGlue()); // "Wypełniacz" - będzie się rozciągał, aby zająć całą dostępną przestrzeń


        addButton = new JButton("+");
        addButton.addActionListener(e -> {
            addButtonListener.add();
            revalidate();
            repaint();
        });

        removeButton = new JButton("-");
        removeButton.addActionListener(e -> {
            removeButtonListener.remove();
            revalidate();
            repaint();
            });

        NetworkPanel.add(Box.createHorizontalGlue());

        scrollPane = new JScrollPane(NetworkPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createHorizontalGlue());

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void updateView(NetworkM item) {
        NetworkPanel.removeAll();
        for(Object obj: item.getWholeNetwork()){
            if(obj instanceof BTSSectionModel){
                BTSSectionController controller = new BTSSectionController((BTSSectionModel) obj);
                BTSSectionG graphic = new BTSSectionG();
                graphic.init(controller);
                controller.setView(graphic);
                NetworkPanel.add(graphic);
            }
            else {
                BSCSectionController controller = new BSCSectionController((BSCSectionModel) obj);
                BSCSectionG graphic = new BSCSectionG();
                graphic.init(controller);
                controller.setView(graphic);
                NetworkPanel.add(graphic);
            }
        }
        revalidate();
        repaint();
    }
}
