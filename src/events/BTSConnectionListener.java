package events;

import models.BTSM;

public interface BTSConnectionListener {
    void addBTS(BTSM bts);
    void removeBTS(String id);
}

