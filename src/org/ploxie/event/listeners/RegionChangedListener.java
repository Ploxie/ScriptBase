package org.ploxie.event.listeners;

import org.ploxie.event.EventListener;
import org.ploxie.event.types.RegionChangedEvent;

public interface RegionChangedListener extends EventListener {

    void notify(RegionChangedEvent event);

}
