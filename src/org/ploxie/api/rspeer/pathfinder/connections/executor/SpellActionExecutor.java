package org.ploxie.api.rspeer.pathfinder.connections.executor;

import org.ploxie.pathfinder.web.connections.SpellConnection;
import org.ploxie.pathfinder.web.connections.executor.NodeConnectionExecutor;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Tab;
import org.rspeer.runetek.api.component.tab.Tabs;

public class SpellActionExecutor implements NodeConnectionExecutor<SpellConnection> {
    @Override
    public boolean execute(SpellConnection connection) {
        InterfaceComponent spell = Interfaces.lookup(connection.getSpell().getAddress());
        if(spell.isVisible()){
            return spell.interact("Cast");
        }else if(!Tab.MAGIC.isOpen()){
            Tabs.open(Tab.MAGIC);
        }
        return false;
    }

    @Override
    public Class<SpellConnection> getType() {
        return SpellConnection.class;
    }
}
