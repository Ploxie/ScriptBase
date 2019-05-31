package org.ploxie.pathfinder.web.path;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.ReachMap2;
import org.ploxie.pathfinder.web.WebNode;

import java.awt.*;

public class LocalPath extends WebPath {

    @Override
    public void traverse() {
        for(int i = size()-1;i > 0; i--){
            WebNode node = get(i);
            boolean walkable = ReachMap2.canReach(node);
            if(walkable){
                Walker2.walkTo(node);
                return;
            }
        }
    }

    @Override
    public void draw(Graphics g) {

    }
}
