package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.wrapper.Position;

public class ObjectConnection extends NodeConnection {

    private String name;
    private String action;
    private Position position;

    public ObjectConnection(Node source, Node target, String objectName, String objectAction) {
        super(source, target);
        this.name = objectName;
        this.action = objectAction;
    }

    public ObjectConnection(Node source, Node target, String objectName, String objectAction, Position position) {
        super(source, target);
        this.name = objectName;
        this.action = objectAction;
        this.position = position;
    }

    public String getObjectName(){
        return name;
    }

    public String getInteractionString(){
        return action;
    }

    public Position getPosition(){
        return position;
    }
}
