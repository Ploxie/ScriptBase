package org.ploxie.pathfinder.wrapper;

import org.ploxie.api.rspeer.pathfinder.collision.RSPeerLocalRegion;
import org.ploxie.pathfinder.collision.region.Region;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.wrapper.Position;
import org.rspeer.RSPeer;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;

public class RSPeerWalker2 extends AbstractWalker2 {

    public RSPeerWalker2(Web web) {
        super(web);
    }

    @Override
    public Position getPlayerPosition() {
        org.rspeer.runetek.api.movement.position.Position pos = Players.getLocal().getPosition();
        return new Position(pos.getX(), pos.getY(), pos.getFloorLevel());
    }

    @Override
    public Region getLocalRegion() {
        int baseX = RSPeer.getClient().getBaseX();
        int baseY = RSPeer.getClient().getBaseY();
        int baseZ = RSPeer.getClient().getFloorLevel();
        return new RSPeerLocalRegion(new Position(baseX,baseY,baseZ), RSPeer.getClient().getCollisionMaps()[baseZ].getFlags());
    }
}
