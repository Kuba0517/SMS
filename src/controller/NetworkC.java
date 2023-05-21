package controller;

import events.AddButtonListener;
import events.NetworkGettable;
import events.RemoveButtonListener;
import events.ViewUpdateListener;
import graphics.NetworkG;
import models.*;

public class NetworkC implements AddButtonListener, RemoveButtonListener, ViewUpdateListener<NetworkM>, NetworkGettable {
    private NetworkM model;
    private ViewUpdateListener<NetworkM> view;

    public NetworkC(NetworkM model){
        this.model = model;
        model.addViewUpdateListener(this);
    }

    public void setView(ViewUpdateListener<NetworkM> view) {
        this.view = view;
        model.addViewUpdateListener(view);
        updateView(model);
    }

    @Override
    public void add() {
        BSCM bscm = new BSCM();
        model.addBscm(bscm);
    }

    @Override
    public void remove() {
        model.removeBscm();
    }

    @Override
    public void updateView(NetworkM item) {
        if(view != null){
            view.updateView(item);
            System.out.println("Aktualizacja widoku");
        }
    }

    @Override
    public NetworkM getNetworkM() {
        return model;
    }
}

