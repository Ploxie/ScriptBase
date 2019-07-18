package org.ploxie.api.rspeer.scripts;


import org.ploxie.event.EventManager;
import org.ploxie.event.listeners.RegionChangedListener;
import org.ploxie.event.observer.types.RegionChangedObserver;
import org.ploxie.event.types.RegionChangedEvent;
import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.WebFileIO;
import org.ploxie.pathfinder.wrapper.RSPeerWalker2;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.awt.*;
import java.io.File;

@ScriptMeta(name = "RegionData Recorder", desc = "", developer = "Ploxie", category = ScriptCategory.OTHER, version = 1.0)
public class RegionRecorder extends Script implements RenderListener, RegionChangedListener {

    @Override
    public void onStart() {
        Web web = WebFileIO.loadWeb(new File("C:\\Users\\Ploxie\\Documents\\data.web"));
        Walker2.setInternalWalker(new RSPeerWalker2(web));

        EventManager.create();
        EventManager.getInstance().register(new RegionChangedObserver());
        EventManager.getInstance().register(this);
    }

    @Override
    public int loop() {
        EventManager.getInstance().update();

        if(Movement.isDestinationSet()){
            return 1000;
        }




        return 1000;
    }

    @Override
    public void notify(RenderEvent renderEvent) {

        Graphics g = renderEvent.getSource();

        g.setColor(Color.RED);

        /*for (Position position : map.keySet()) {
            org.rspeer.runetek.api.movement.position.Position p = new org.rspeer.runetek.api.movement.position.Position(position.getX(), position.getY(), position.getZ());
            Point point = Projection.toMinimap(p);
            Polygon s = Projection.getTileShape(p);
            if (point != null) {
                g.fillRect(point.x, point.y, 3, 3);
            }

            /*if(s != null){
                g.fillPolygon(s);
            }*/
        //}

        /*Position playerPosition = Walker2.getLocalPlayerPosition();

        LocalRegion localRegion = Walker2.getLocalRegion();

        for(int y = -1; y <= 1;y++){
            for(int x = -1; x <= 1;x++){
                if(localRegion.canGoInDirection(playerPosition, x, y)){
                    org.rspeer.runetek.api.movement.position.Position p = new org.rspeer.runetek.api.movement.position.Position(playerPosition.getX()+x, playerPosition.getY()+y, playerPosition.getZ());
                    Polygon poly = Projection.getTileShape(p);
                    if(poly != null){
                        g.drawPolygon(poly);
                    }
                }
            }
        }*/


    }

    @Override
    public void notify(RegionChangedEvent event) {
    }
}
