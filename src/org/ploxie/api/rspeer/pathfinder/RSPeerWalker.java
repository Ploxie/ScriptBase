package org.ploxie.api.rspeer.pathfinder;

import org.ploxie.api.rspeer.pathfinder.connections.executor.*;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.area.WebArea;
import org.ploxie.pathfinder.wrapper.DefaultWalker;

public class RSPeerWalker extends DefaultWalker {

    public RSPeerWalker(Web web) {
        super(web);

        addConnectionExecutor(new ItemActionExecutor());
        addConnectionExecutor(new ObjectActionExecutor());
        addConnectionExecutor(new NpcActionExecutor());
        addConnectionExecutor(new SpellActionExecutor());
        addConnectionExecutor(new NodeWalkExecutor());
        addConnectionExecutor(new TileWalkExecutor());
        addConnectionExecutor(new ItemLootExecutor());

        web.addWebArea(new WebArea.Commons());
        web.addWebArea(new WebArea.Ladders());
        web.addWebArea(new WebArea.Banks());
        web.addWebArea(new WebArea.Teleports());

        //setReachable(new MinimapReachable());
    }

}
