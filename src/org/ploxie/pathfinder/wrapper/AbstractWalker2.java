package org.ploxie.pathfinder.wrapper;


import org.ploxie.pathfinder.collision.region.Region;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.wrapper.Position;

public abstract class AbstractWalker2 {

    protected Web web;

    public AbstractWalker2(Web web) {
        this.web = web;
    }

    public abstract Position getPlayerPosition();

    public abstract Region getLocalRegion();

}
