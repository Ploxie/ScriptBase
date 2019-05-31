package org.ploxie.event;

public abstract class Event {

    public abstract void forward(EventListener listener);

}
