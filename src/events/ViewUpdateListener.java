package events;

import models.NetworkDevice;

public interface ViewUpdateListener<T> {
    void updateView(T item);
}
