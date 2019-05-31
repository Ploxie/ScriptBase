package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.ReachMap2;
import org.ploxie.pathfinder.web.WebNode;
import org.ploxie.pathfinder.web.connections.WalkConnection2;
import org.ploxie.pathfinder.web.connections.WebNodeConnection;
import org.ploxie.pathfinder.wrapper.Position;

import java.awt.*;

public class NodePath2 extends WebPath {

    private LocalPath localPath;

    @Override
    public void traverse() {

    }

    @Override
    public void draw(Graphics g) {

        LocalPath localPath = toLocalPath();
        if(localPath != null){
            localPath.draw(g);
        }
    }

    public LocalPath toLocalPath(){
        if(localPath == null){
            localPath = (LocalPath) Walker2.findLocalPath(getLastWalkableNode());
        }
        return localPath;
    }

    protected WebNode getLastWalkableNode(){
        WebNode start = getFirst();
        WebNode end = getLast();

        WebNode last = start;
        WebNode current;
        for (current = getNext(start); !current.equals(end); current = getNext(current)) {
            if (!ReachMap2.canReach(current)) {
                break;
            }

            WebNodeConnection connection = last.getConnection(current);
            if (connection == null || (connection instanceof WalkConnection2)) {
                break;
            }

            last = current;
        }

        return last;
    }

    public WebNode getNearestNode(Position target) {
        WebNode best = null;
        double distance = Double.MAX_VALUE;
        for (WebNode node : this) {
            double currentDistance = node.distanceTo(target);
            if (node.distanceTo(target) < distance) {
                best = node;
                distance = currentDistance;
            }
        }

        return best;
    }


}
