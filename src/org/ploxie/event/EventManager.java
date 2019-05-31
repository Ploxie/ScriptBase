package org.ploxie.event;

import org.ploxie.event.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static EventManager instance;

    private List<EventListener> listeners = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    protected EventManager(){

    }

    public void register(Observer observer){
        observers.add(observer);
    }

    public void register(EventListener listener){
        listeners.add(listener);
    }

    public void deregister(Observer observer){
        observers.remove(observer);
    }

    public void deregister(EventListener listener){
        listeners.remove(listener);
    }

    public void dispatch(Event event){
        for(EventListener listener : listeners){
            event.forward(listener);
        }
    }

    public void update(){
        for(Observer observer : observers){
            observer.update();
        }
    }

    public static EventManager create(){
        if(instance == null){
            instance = new EventManager();
        }
        return instance;
    }

    public static EventManager getInstance(){
        return instance;
    }

}
