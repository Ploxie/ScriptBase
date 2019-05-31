package org.ploxie.api.rspeer.pathfinder;

import org.ploxie.api.rspeer.pathfinder.collision.MinimapReachable;
import org.ploxie.api.rspeer.pathfinder.connections.executor.NodeWalkExecutor;
import org.ploxie.api.rspeer.pathfinder.connections.executor.ObjectActionExecutor;
import org.ploxie.api.rspeer.pathfinder.connections.executor.TileWalkExecutor;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.wrapper.DefaultWalker;

public class RSPeerWalker extends DefaultWalker {

    public RSPeerWalker(Web web) {
        super(web);

        addConnectionExecutor(new ObjectActionExecutor());
        addConnectionExecutor(new NodeWalkExecutor());
        addConnectionExecutor(new TileWalkExecutor());

        //setReachable(new MinimapReachable());
    }

}
