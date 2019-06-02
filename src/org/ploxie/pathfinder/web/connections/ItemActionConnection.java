package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;
import org.rspeer.runetek.api.component.tab.Inventory;

public class ItemActionConnection extends NodeConnection {

    private int itemID;
    private String action;

    public ItemActionConnection(Node source, Node target, int itemID, String action) {
        super(source, target);
        this.itemID = itemID;
        this.action = action;
    }

    public int getItemID(){
        return itemID;
    }

    public String getAction(){
        return action;
    }

    @Override
    public boolean canUse() {
        return Inventory.contains(itemID);
    }

    @Override
    public Class<? extends NodeConnection> getType() {
        return ItemActionConnection.class;
    }
}
