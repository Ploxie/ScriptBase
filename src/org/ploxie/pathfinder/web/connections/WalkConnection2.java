package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.ReachMap2;
import org.ploxie.pathfinder.web.WebNode;
import org.ploxie.pathfinder.web.path.WebPath;
import org.rspeer.ui.Log;

public class WalkConnection2 extends WebNodeConnection {

    public WalkConnection2(WebNode source, WebNode target) {
        super(source, target);
    }

    @Override
    public void traverse() {
        WebPath localPath = Walker2.findLocalPath(target);
        if(localPath != null){
            localPath.traverse();
            Log.info("Traversing");
            return;
        }

        localPath = Walker2.findLocalPath(ReachMap2.getClosestTo(target));
        if(localPath != null){
            localPath.traverse();
            Log.info("Traversing closest");
            return;
        }

        Log.info("NULL");


    }

}
