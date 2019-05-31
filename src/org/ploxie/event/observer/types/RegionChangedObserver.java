package org.ploxie.event.observer.types;

import org.ploxie.event.observer.Observer;
import org.ploxie.event.types.RegionChangedEvent;
import org.rspeer.RSPeer;

public class RegionChangedObserver extends Observer {

    private int baseX;
    private int baseY;
    private int baseZ;

    @Override
    public void update() {
        int x = RSPeer.getClient().getBaseX();
        int y = RSPeer.getClient().getBaseY();
        int z = RSPeer.getClient().getFloorLevel();

        if(x == baseX && y == baseY && baseZ == z){
            return;
        }

        baseX = x;
        baseY = y;
        baseZ = z;

        RegionChangedEvent event = new RegionChangedEvent();
        dispatch(event);
    }
}
