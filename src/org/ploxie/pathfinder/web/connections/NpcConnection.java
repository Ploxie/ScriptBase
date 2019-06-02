package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.wrapper.Position;

public class NpcConnection extends NodeConnection {

    private Position position;
    private String npcName;
    private String action;

    public NpcConnection(Node source, Node target, Position position, String name, String action) {
        super(source, target);

        this.position = position;
        this.npcName = name;
        this.action = action;
    }

    public NpcConnection(Node source, Node target, String name, String action) {
        super(source, target);

        this.npcName = name;
        this.action = action;
    }

    public Position getPosition() {
        return position;
    }

    public String getNpcName() {
        return npcName;
    }

    public String getAction() {
        return action;
    }

    @Override
    public Class<? extends NodeConnection> getType() {
        return NpcConnection.class;
    }
}
