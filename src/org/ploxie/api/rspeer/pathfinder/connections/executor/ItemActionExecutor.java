package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.web.connections.ItemActionConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.ui.Log;

public class ItemActionExecutor implements NodeConnectionExecutor<ItemActionConnection> {

    @Override
    public boolean execute(ItemActionConnection connection) {
        Item item = Inventory.getFirst(connection.getItemID());

        Log.info("ITEM INTERACTION");
        if(item == null){
            return false;
        }


        return item.interact(connection.getAction());
    }

    @Override
    public Class<ItemActionConnection> getType() {
        return ItemActionConnection.class;
    }
}
