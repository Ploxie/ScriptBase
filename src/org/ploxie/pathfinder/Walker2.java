package org.ploxie.pathfinder;

import org.ploxie.pathfinder.collision.region.Region;
import org.ploxie.pathfinder.wrapper.AbstractWalker2;
import org.ploxie.wrapper.Position;

public class Walker2 {

    private static AbstractWalker2 internalWalker;

    public static Position getLocalPlayerPosition(){
        return internalWalker.getPlayerPosition();
    }

    public static Region getLocalRegion(){
        return internalWalker.getLocalRegion();
    }

    public static void setInternalWalker(AbstractWalker2 walker){
        internalWalker = walker;
    }


}
