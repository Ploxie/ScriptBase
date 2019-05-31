package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.wrapper.Position;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.Comparator;
import java.util.function.Predicate;

public class ObjectActionConnection extends NodeConnection {

    private String name;
    private String action;
    private Position position;

    public ObjectActionConnection(Node source, Node target, String objectName, String objectAction) {
        super(source, target);
        this.name = objectName;
        this.action = objectAction;
    }

    public ObjectActionConnection(Node source, Node target, String objectName, String objectAction, Position position) {
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
