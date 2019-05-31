package org.ploxie.event.observer;

import org.ploxie.event.Event;
import org.ploxie.event.EventManager;

public abstract class Observer {

    public abstract void update();

    protected void dispatch(Event event){
        EventManager.getInstance().dispatch(event);
    }

}
