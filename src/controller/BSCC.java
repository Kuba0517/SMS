package controller;

import graphics.BSCG;
import models.BSCM;
import models.BTSM;
import events.BTSConnectionListener;

public class BSCC implements BTSConnectionListener {
    private BSCM model;
    private BSCG graphic;

    public BSCC(BSCM model, BSCG view) {
        this.model = model;
        this.graphic = graphic;
    }

    public BSCG getGraphic(){
        return graphic;
    }

    @Override
    public void addBTS(BTSM bts) {
        model.addBTS(bts);
        updateGraphic();
    }

    @Override
    public void removeBTS(String id) {
        // Implementacja logiki usuwania BTS
        updateGraphic();
    }

    private void updateGraphic() {
        // Aktualizuj widok na podstawie stanu modelu
    }
}

