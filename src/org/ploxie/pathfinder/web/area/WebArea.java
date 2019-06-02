package org.ploxie.pathfinder.web.area;

import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.connections.*;
import org.ploxie.pathfinder.web.node.DynamicNode;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.web.node.WebNode;
import org.ploxie.pathfinder.web.webbank.WebBankNode;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;

public abstract class WebArea {

    public abstract void addNodes(Web web);

    public static class Ladders extends WebArea {
        @Override
        public void addNodes(Web web) {

            WebNode lumbridgeCenter = web.getNearestStaticNode(3223, 3219, 0);

            WebNode lumbridgeCastleWallNorthBottom = web.getNode(3229, 3223, 0);
            WebNode lumbridgeCastleWallNorthMiddle = web.getNode(3229, 3223, 1);

            WebNode lumbridgeCastleWallSouthBottom = web.getNode(3229, 3214, 0);
            WebNode lumbridgeCastleWallSouthMiddle = web.getNode(3229, 3214, 1);

            WebNode lumbridgeCastleWallTop = web.getNode(3229, 3218, 2);

            web.addConnection(lumbridgeCenter, lumbridgeCastleWallNorthBottom);
            web.addConnection(lumbridgeCenter, lumbridgeCastleWallSouthBottom);

            web.addConnection(new ObjectConnection(lumbridgeCastleWallNorthBottom, lumbridgeCastleWallNorthMiddle, "Ladder", "Climb-up", new Position(3229, 3224, 0)));
            web.addConnection(new ObjectConnection(lumbridgeCastleWallNorthMiddle, lumbridgeCastleWallNorthBottom, "Ladder", "Climb-down", new Position(3229, 3224, 1)));
            web.addConnection(new ObjectConnection(lumbridgeCastleWallNorthMiddle, lumbridgeCastleWallTop, "Ladder", "Climb-up", new Position(3229, 3224, 1)));
            web.addConnection(new ObjectConnection(lumbridgeCastleWallTop, lumbridgeCastleWallNorthMiddle, "Ladder", "Climb-down", new Position(3229, 3224, 2)));

            web.addConnection(new ObjectConnection(lumbridgeCastleWallSouthBottom, lumbridgeCastleWallSouthMiddle, "Ladder", "Climb-up", new Position(3229, 3213, 0)));
            web.addConnection(new ObjectConnection(lumbridgeCastleWallSouthMiddle, lumbridgeCastleWallSouthBottom, "Ladder", "Climb-down", new Position(3229, 3213, 1)));
            web.addConnection(new ObjectConnection(lumbridgeCastleWallSouthMiddle, lumbridgeCastleWallTop, "Ladder", "Climb-up", new Position(3229, 3213, 1)));
            web.addConnection(new ObjectConnection(lumbridgeCastleWallTop, lumbridgeCastleWallSouthMiddle, "Ladder", "Climb-down", new Position(3229, 3213, 2)));

        }

    }

    public static class Banks extends WebArea {
        @Override
        public void addNodes(Web web) {
            WebBankNode edgevilleBank = new WebBankNode(3093, 3494, 0);
            WebBankNode varrockWestBank = new WebBankNode(3182, 3441, 0);
            WebBankNode varrockEastBank = new WebBankNode(3253, 3421, 0);
            WebBankNode grandExchange = new WebBankNode(3164, 3486, 0);


            web.add(edgevilleBank);
            web.add(varrockWestBank);
            web.add(varrockEastBank);
            web.add(grandExchange);
        }
    }

    public static class Teleports extends WebArea {
        @Override
        public void addNodes(Web web) {
            DynamicNode playerNode = new DynamicNode(){

                @Override
                public Position getPosition() {
                    return Walker2.getLocalPlayerPosition();
                }
            };

            /*DynamicNode nearestNode = new DynamicNode(){

                @Override
                public Position getPosition() {
                    return web.getNearestStaticNode(playerNode).getPosition();
                }
            };*/

            DynamicWalkConnection nearestNodeConnection = new DynamicWalkConnection() {
                @Override
                public Node getTarget() {
                    return web.getNearestStaticNode(playerNode);
                }

                @Override
                public Class<? extends NodeConnection> getType() {
                    return NodeWalkConnection.class;
                }

                @Override
                public Node getSource() {
                    return playerNode;
                }
            };

        }
    }
}
