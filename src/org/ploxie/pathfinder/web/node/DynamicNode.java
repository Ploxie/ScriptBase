package org.ploxie.pathfinder.web.node;

import org.ploxie.wrapper.Position;


public abstract class DynamicNode extends WebNode {

    public DynamicNode() {
        super(0,0,0);
    }

    public abstract Position getPosition();

}
