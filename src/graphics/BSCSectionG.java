package graphics;

import controller.BSCC;
import events.MultiSection;
import events.ViewUpdateListener;
import models.BSCM;
import models.BSCSectionModel;

import javax.swing.*;
import java.awt.*;

public class BSCSectionG extends JPanel implements ViewUpdateListener<BSCSectionModel> {
    private MultiSection bscSection;
    private JPanel bscPanel;

    public BSCSectionG() {
        setLayout(new BorderLayout());

        bscPanel = new JPanel();
        bscPanel.setLayout(new BoxLayout(bscPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(bscPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void init(MultiSection bscSection){
        this.bscSection = bscSection;
        initializeComponents();
    }

    private void initializeComponents() {
        for (Object bscm : bscSection.getItems()) {
            BSCC controller = new BSCC((BSCM) bscm);
            BSCG bscg = new BSCG();
            bscg.init(controller);
            controller.setView(bscg);
            bscPanel.add(bscg);
        }
    }

    @Override
    public void updateView(BSCSectionModel item) {
        bscPanel.removeAll();
        initializeComponents();
        bscPanel.revalidate();
        bscPanel.repaint();
    }
}

