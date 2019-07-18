package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;

public class ItemLootConnection extends NodeConnection {

    private int itemID;

    public ItemLootConnection(Node source, Node target, int itemID) {
        super(source, target);
        this.itemID = itemID;
    }


    @Override
    public Class<? extends NodeConnection> getType() {
        return ItemLootConnection.class;
    }

    public int getItemID() {
        return itemID;
    }
}
