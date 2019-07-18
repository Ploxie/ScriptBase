package org.ploxie.pathfinder.web.connections;

import org.ploxie.pathfinder.web.node.Node;
import org.rspeer.runetek.api.component.tab.Spell;

public class SpellConnection extends NodeConnection {

    private Spell spell;

    public SpellConnection(Node source, Node target, Spell spell) {
        super(source, target);
        this.spell = spell;
    }

    @Override
    public Class<? extends NodeConnection> getType() {
        return SpellConnection.class;
    }

    public Spell getSpell() {
        return spell;
    }
}
