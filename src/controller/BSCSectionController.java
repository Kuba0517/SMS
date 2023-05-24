package controller;

import events.MultiSection;
import events.ViewUpdateListener;
import models.BSCM;
import models.BSCSectionModel;

import java.util.ArrayList;

public class BSCSectionController implements ViewUpdateListener<BSCSectionModel>, MultiSection {
    private BSCSectionModel model;
    private ViewUpdateListener<BSCSectionModel> view;

    public BSCSectionController(BSCSectionModel model) {
        this.model = model;
        model.setViewUpdateListener(this);
    }

    @Override
    public void addItems() {
        model.addBSCM();
    }

    @Override
    public ArrayList getItems() {
        return model.getBSCMS();
    }


    @Override
    public void updateView(BSCSectionModel item) {
        if(view != null){
            view.updateView(item);
        }
    }

    public void setView(ViewUpdateListener<BSCSectionModel> view) {
        this.view = view;
    }
}

