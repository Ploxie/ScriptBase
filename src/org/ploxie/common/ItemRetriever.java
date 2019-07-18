package org.ploxie.common;

import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.methods.astar.AStar;
import org.ploxie.pathfinder.methods.astar.AStarEndCondition;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.node.ItemRetrievedNode;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.pathfinder.web.path.Path;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;

public class ItemRetriever {

    private static AStarEndCondition retrieveCondition(int id, int count){
        return node -> (node instanceof ItemRetrievedNode) && ((ItemRetrievedNode)node).getItemID() == id && ((ItemRetrievedNode)node).getItemCount() >= count;
    }

    public static boolean retrieveItem(int id, int count){
        Web web = Walker.getInstance().getWeb();

        if(Inventory.getCount(true, id) >= count){
            return true;
        }

        Path path = new AStar().buildPath(web.getNearestNode(Walker2.getLocalPlayerPosition()), retrieveCondition(id, count));
        if(path != null){
            Log.info("RETRIEVING");
            return path.traverse();
        }

        Log.info("CANT RETRIEVE");

        return false;
    }

}
