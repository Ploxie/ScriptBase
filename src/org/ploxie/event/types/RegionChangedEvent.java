package org.ploxie.event.types;

import org.ploxie.event.Event;
import org.ploxie.event.EventListener;
import org.ploxie.event.listeners.RegionChangedListener;

public class RegionChangedEvent extends Event {

    @Override
    public void forward(EventListener listener) {
        if(listener instanceof RegionChangedListener){
            ((RegionChangedListener)listener).notify(this);
        }
    }

}
