package events;

import models.VBDM;

public interface ViewUpdateListener<T> {
    void updateView(T item);
}
