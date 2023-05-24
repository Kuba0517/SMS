package controller;

import events.MultiSection;
import events.ViewUpdateListener;
import models.BTSM;
import models.BTSSectionModel;

import java.util.ArrayList;

public class BTSSectionController implements ViewUpdateListener<BTSSectionModel>, MultiSection {
    private BTSSectionModel model;
    private ViewUpdateListener<BTSSectionModel> view;

    public BTSSectionController(BTSSectionModel model) {
        this.model = model;
        model.setViewUpdateListener(this);
    }

    @Override
    public void addItems() {
        model.addBTSM();
    }

    @Override
    public ArrayList getItems() {
        return model.getBTSMS();
    }


    @Override
    public void updateView(BTSSectionModel item) {
        if(view != null){
            view.updateView(item);
        }
    }

    public void setView(ViewUpdateListener<BTSSectionModel> view) {
        this.view = view;
    }
}
