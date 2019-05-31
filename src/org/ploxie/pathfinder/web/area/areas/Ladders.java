package org.ploxie.pathfinder.web.area.areas;

import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.area.WebArea;
import org.ploxie.pathfinder.web.connections.ObjectConnection;
import org.ploxie.pathfinder.web.node.WebNode;
import org.ploxie.wrapper.Position;

public class Ladders extends WebArea {

    @Override
    public void addNodes(Web web) {

        WebNode lumbridgeCenter = web.getNearestNode(3223,3219,0);

        WebNode lumbridgeCastleWallNorthBottom = web.getNode(3229,3223,0);
        WebNode lumbridgeCastleWallNorthMiddle = web.getNode(3229,3223,1);

        WebNode lumbridgeCastleWallSouthBottom = web.getNode(3229,3214,0);
        WebNode lumbridgeCastleWallSouthMiddle = web.getNode(3229,3214,1);

        WebNode lumbridgeCastleWallTop = web.getNode(3229,3218,2);


        web.addConnection(lumbridgeCenter,lumbridgeCastleWallNorthBottom);
        web.addConnection(lumbridgeCenter,lumbridgeCastleWallSouthBottom);

        web.addConnection(new ObjectConnection(lumbridgeCastleWallNorthBottom,lumbridgeCastleWallNorthMiddle, "Ladder", "Climb-up", new Position(3229,3224,0)));
        web.addConnection(new ObjectConnection(lumbridgeCastleWallNorthMiddle,lumbridgeCastleWallNorthBottom, "Ladder", "Climb-down", new Position(3229,3224,1)));
        web.addConnection(new ObjectConnection(lumbridgeCastleWallNorthMiddle,lumbridgeCastleWallTop, "Ladder", "Climb-up", new Position(3229,3224,1)));
        web.addConnection(new ObjectConnection(lumbridgeCastleWallTop,lumbridgeCastleWallNorthMiddle, "Ladder", "Climb-down", new Position(3229,3224,2)));

        web.addConnection(new ObjectConnection(lumbridgeCastleWallSouthBottom,lumbridgeCastleWallSouthMiddle, "Ladder", "Climb-up", new Position(3229,3213,0)));
        web.addConnection(new ObjectConnection(lumbridgeCastleWallSouthMiddle,lumbridgeCastleWallSouthBottom, "Ladder", "Climb-down", new Position(3229,3213,1)));
        web.addConnection(new ObjectConnection(lumbridgeCastleWallSouthMiddle,lumbridgeCastleWallTop, "Ladder", "Climb-up", new Position(3229,3213,1)));
        web.addConnection(new ObjectConnection(lumbridgeCastleWallTop,lumbridgeCastleWallSouthMiddle, "Ladder", "Climb-down", new Position(3229,3213,2)));


    }

}
