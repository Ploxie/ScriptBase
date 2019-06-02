package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.web.connections.NpcConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.ui.Log;

public class NpcActionExecutor implements NodeConnectionExecutor<NpcConnection> {
    @Override
    public boolean execute(NpcConnection connection) {
        Npc object = Npcs.getBest((o1, o2) -> {
            Position pos = new Position(o1.getX(), o1.getY(), o1.getFloorLevel());
            Position otherPos = new Position(o2.getX(), o2.getY(), o2.getFloorLevel());
            return pos.distanceTo(otherPos);
        }, npc -> {
            if (connection.getPosition() != null) {
                if (npc.getPosition().getX() != connection.getPosition().getX() || npc.getPosition().getY() != connection.getPosition().getY() || npc.getPosition().getFloorLevel() != connection.getPosition().getZ()) {
                    return false;
                }
            }
            if (!npc.getName().equalsIgnoreCase(connection.getNpcName())) {
                return false;
            }
            for (String action : npc.getActions()) {
                if (action.equalsIgnoreCase(action)) {
                    return true;
                }
            }
            return false;
        });

        Log.info("NPC INTERACTION");


        if(object != null){
            return object.interact(connection.getAction());
        }else{
            Log.info("Npc is null "+connection.getPosition());
        }
        return false;
    }

    @Override
    public Class<NpcConnection> getType() {
        return NpcConnection.class;
    }
}
