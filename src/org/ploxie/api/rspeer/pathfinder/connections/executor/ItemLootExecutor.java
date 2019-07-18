package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.web.connections.ItemLootConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.ui.Log;


public class ItemLootExecutor implements NodeConnectionExecutor<ItemLootConnection> {
    @Override
    public boolean execute(ItemLootConnection connection) {


        Pickable loot = Pickables.getNearest(connection.getItemID());

        if(loot != null){
            Log.info("LOOTING");
            return loot.click();
        }else{
            Log.info("LOOT IS NULL");
        }



        return false;
    }

    @Override
    public Class<ItemLootConnection> getType() {
        return ItemLootConnection.class;
    }
}
