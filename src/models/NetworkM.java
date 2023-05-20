package models;

import events.ViewUpdateListener;
import java.util.ArrayList;

public class NetworkM {
    private ArrayList<BSCM> bscmList;
    private ArrayList<ViewUpdateListener<NetworkM>> listeners;
    private BTSM startBtsm;
    private BTSM endBtsm;
    private ArrayList<Object> wholeNetwork;

    public NetworkM(){
        bscmList = new ArrayList<>();
        startBtsm = new BTSM();
        endBtsm = new BTSM();
        listeners = new ArrayList<>();
        wholeNetwork = new ArrayList<>();
        updateWholeNetwork();
    }

    public void addBscm(BSCM bscm){
        bscmList.add(bscm);
        fireViewUpdate();
    }

    public void removeBscm(){
        if (!bscmList.isEmpty()) {
            bscmList.remove(bscmList.size()-1);
            fireViewUpdate();
        }
    }

    public BTSM getStartBtsm() {
        return startBtsm;
    }

    public void setStartBtsm(BTSM startBtsm) {
        this.startBtsm = startBtsm;
    }

    public BTSM getEndBtsm() {
        return endBtsm;
    }

    public void setEndBtsm(BTSM endBtsm) {
        this.endBtsm = endBtsm;
    }

    public ArrayList<BSCM> getBscmList() {
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
