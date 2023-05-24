package controller;

import events.*;
import models.NetworkDevice;
import models.ReceiverM;
import models.VRDM;


public class ReceiverC implements AddButtonListener, ViewUpdateListener<ReceiverM>, RemoveListener {
    private ReceiverM model;
    private ViewUpdateListener<ReceiverM> view;

    public ReceiverC(ReceiverM model){
        this.model = model;
        model.setViewUpdateListener(this);
    }

    public void setView(ViewUpdateListener<ReceiverM> view) {
        this.view = view;
        model.setViewUpdateListener(view);
    }

    @Override
    public void add(){
        VRDM vrdm = new VRDM(this);
        model.addVRD(vrdm);
    }

    @Override
    public void remove(Device device) {
        model.removeVRD((VRDM) device);
    }


    @Override
    public void updateView(ReceiverM item) {
        System.out.println("Aktualizacjjjjjajajaja");
        if(view != null){
            view.updateView(item);
            System.out.println("Aktualizacja widoku");
        }
    }
}

