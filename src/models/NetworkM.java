package models;

import events.ViewUpdateListener;
import java.util.ArrayList;

public class NetworkM {
    private ArrayList<ViewUpdateListener<NetworkM>> listeners;
    private BTSSectionModel startBtsm;
    private ArrayList<BSCSectionModel> bscmList;
    private BTSSectionModel endBtsm;
    private ArrayList<Object> wholeNetwork;

    public NetworkM(){
        startBtsm = new BTSSectionModel();
        bscmList = new ArrayList<>();
        bscmList.add(new BSCSectionModel());
        endBtsm = new BTSSectionModel();
        listeners = new ArrayList<>();
        wholeNetwork = new ArrayList<>();
        updateWholeNetwork();
    }

    public void addBscm(){
        bscmList.add(new BSCSectionModel());
        fireViewUpdate();
    }

    public void removeBscm(){
        if (!bscmList.isEmpty()) {
            bscmList.remove(bscmList.size()-1);
            fireViewUpdate();
        }
    }

    public BTSSectionModel getStartBtsm() {
        return startBtsm;
    }

    public void setStartBtsm(BTSSectionModel startBtsm) {
        this.startBtsm = startBtsm;
    }

    public BTSSectionModel getEndBtsm() {
        return endBtsm;
    }

    public void setEndBtsm(BTSSectionModel endBtsm) {
        this.endBtsm = endBtsm;
    }

    public ArrayList<BSCSectionModel> getBscmList() {
        return bscmList;
    }

    public void addViewUpdateListener(ViewUpdateListener<NetworkM> listener) {
        listeners.add(listener);
    }

    public void updateWholeNetwork(){
        wholeNetwork.clear();
        wholeNetwork.add(startBtsm);
        wholeNetwork.addAll(bscmList);
        wholeNetwork.add(endBtsm);
    }

    public ArrayList<Object> getWholeNetwork(){
        return wholeNetwork;
    }

    private void fireViewUpdate(){
        updateWholeNetwork();
        for(ViewUpdateListener<NetworkM> network : listeners){
            network.updateView(this);
        }
    }
}
