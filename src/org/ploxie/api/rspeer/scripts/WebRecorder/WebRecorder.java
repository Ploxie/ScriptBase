package org.ploxie.api.rspeer.scripts.WebRecorder;


import org.ploxie.api.rspeer.pathfinder.RSPeerWalker;
import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.collision.region.Region;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.WebFileIO;
import org.ploxie.pathfinder.wrapper.RSPeerWalker2;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ScriptMeta(name = "Web Recorder", desc = "", developer = "Ploxie", category = ScriptCategory.OTHER, version = 1.0)
public class WebRecorder extends Script implements RenderListener {

    private List<NodeTile> tiles = new ArrayList<>();


    @Override
    public void onStart() {
        Web web = WebFileIO.loadWeb(new File("C:\\Users\\Ploxie\\Documents\\data.web"));

        Walker2.setInternalWalker(new RSPeerWalker2(web));


        Walker.create(new RSPeerWalker(web));
    }

    private NodeTile getCurrentTile(Position position) {

        int radius = 0;
        for (int i = 1; i < 20; i++) {
            if (canExpand(position, i)) {
                radius = i;
            }else{
                break;
            }
        }


        return new NodeTile(position, radius);

    }

    private NodeTile getCurrentTile(Position position, int radius){
        for (int i = 0; i <= radius; i++) {
            if (!canExpand(position, i)) {
                break;
            }
            if(i == radius){
                return new NodeTile(position, i);
            }
        }


        return null;
    }

    private void addNodeTiles(Area area, int radius){
        loop: for (org.rspeer.runetek.api.movement.position.Position position : area.getTiles()) {
            NodeTile tile = getCurrentTile(new Position(position.getX(), position.getY(), position.getFloorLevel()), radius);
            if(tile != null){
                for (org.rspeer.runetek.api.movement.position.Position areaPosition : tile.getArea().getTiles()) {
                    if(isInTiles(new Position(areaPosition.getX(), areaPosition.getY(), areaPosition.getFloorLevel())) != null){
                        continue loop;
                    }
                }

                if(!tiles.contains(tile)){
                    tiles.add(tile);
                }
            }
        }
    }

    private boolean canExpand(Position position, int radius) {

        for (int y = 0; y <= radius; y++) {
            for (int x = 0; x <= radius; x++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                NodeTile existing = isInTiles(new Position(x,y,0));
                if(existing != null){
                    return false;
                }

                Region region = Walker2.getLocalRegion();
                boolean canGo = region.canGoInDirection(position, x, y);
                if (!canGo) {
                    return false;
                }


            }
        }

        return true;
    }

    private NodeTile isInTiles(Position position) {
        for (NodeTile nodeTile : tiles) {
            if (nodeTile.getArea().contains(new org.rspeer.runetek.api.movement.position.Position(position.getX(), position.getY(), position.getZ()))) {
                return nodeTile;
            }
        }

        return null;
    }

    @Override
    public int loop() {

        Area area = Area.surrounding(Players.getLocal().getPosition(), 20);
        for(int i = 10; i >= 1;i--){
            addNodeTiles(area, i);
        }


        /*loop:
        for (org.rspeer.runetek.api.movement.position.Position position : area.getTiles()) {

            NodeTile tile = getCurrentTile(new Position(position.getX(), position.getY(), position.getFloorLevel()));
            if(tiles.contains(tile)){
                continue;
            }
            if(tile.getRadius() <= 0){
                continue;
            }
            for(org.rspeer.runetek.api.movement.position.Position areaPosition : tile.getArea().getTiles()){
                NodeTile existing = isInTiles(new Position(areaPosition.getX(), areaPosition.getY(), areaPosition.getFloorLevel()));
                if(existing != null){
                    if(existing.getRadius() < tile.getRadius()){
                        //tiles.remove(existing);
                    }else{
                    }
                    continue loop;
                }
            }

            tiles.add(tile);
        }*/

        return 50;
    }

    @Override
    public void notify(RenderEvent renderEvent) {

        Graphics g = renderEvent.getSource();


        for (NodeTile tile : tiles) {
            g.setColor(tile.getColor());
            tile.getArea().outline(g);

            Point minimap = Projection.toMinimap(tile.getArea().getCenter());
            if(minimap != null){
                g.fillRect(minimap.x, minimap.y, 3,3);
            }
        }


    }
}
