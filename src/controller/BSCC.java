package controller;

import graphics.BSCG;
import models.BSCM;
import models.BTSM;
import events.BTSConnectionListener;

public class BSCC implements BTSConnectionListener {
    private BSCM model;
    private BSCG view;

    public BSCC(BSCM model, BSCG view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void addBTS(BTSM bts) {
        model.addBTS(bts);
        updateView();
    }

    @Override
    public void removeBTS(String id) {
        // Implementacja logiki usuwania BTS
        updateView();
    }

    private void updateView() {
        // Aktualizuj widok na podstawie stanu modelu
    }
}

