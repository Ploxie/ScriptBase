package org.ploxie.api.rspeer.scripts;


import org.ploxie.api.rspeer.pathfinder.RSPeerWalker;
import org.ploxie.api.rspeer.scripts.quester.Quest;
import org.ploxie.api.rspeer.scripts.quester.cooksassistant.CooksAssistant;
import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.Walker2;
import org.ploxie.pathfinder.web.Web;
import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.node.WebNode;
import org.ploxie.pathfinder.web.path.Path;
import org.ploxie.pathfinder.wrapper.RSPeerWalker2;
import org.ploxie.pathfinder.web.WebFileIO;
import org.ploxie.wrapper.Position;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.awt.*;
import java.io.File;

@ScriptMeta(name = "Test Script", desc = "", developer = "Ploxie", category = ScriptCategory.OTHER, version = 1.0)
public class TestScript extends Script implements RenderListener{

    private Web web;
    private Path currentPath;

    private Quest quest = new CooksAssistant();

    @Override
    public void onStart() {
        Web loadedWeb = WebFileIO.loadWeb(new File("C:\\Users\\Ploxie\\Documents\\data.web"));
        this.web = loadedWeb;

        Walker2.setInternalWalker(new RSPeerWalker2(web));


        //Inventory.setInternal(new RSPeerInventory());
        //Players.setInternal(new RSPeerPlayers());

        Walker.create(new RSPeerWalker(web));


    }

    @Override
    public int loop() {

        quest.execute();



        return 1000;
    }

    @Override
    public void notify(RenderEvent renderEvent) {

        Graphics g = renderEvent.getSource();

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        if(g == null){
            return;
        }

        for(WebNode node : web){
            for(NodeConnection connection : node.getConnections()){

                org.rspeer.runetek.api.movement.position.Position pos = new org.rspeer.runetek.api.movement.position.Position(connection.getSource().getX(), connection.getSource().getY(), connection.getSource().getZ());
                org.rspeer.runetek.api.movement.position.Position pos2 = new org.rspeer.runetek.api.movement.position.Position(connection.getTarget().getX(), connection.getTarget().getY(), connection.getTarget().getZ());
                Point p = Projection.toMinimap(pos, true);
                Point p2 = Projection.toMinimap(pos2, true);
                if(p != null){
                    g.fillRect(p.x, p.y, 3,3);
                }
                if(p2 != null){
                    g.fillRect(p2.x, p2.y, 3,3);
                }
                if(p != null && p2 != null){
                    g.drawLine(p.x, p.y, p2.x, p2.y);
                }
            }
        }

        if(currentPath != null){
            for(NodeConnection c : currentPath.getConnections()){
                org.rspeer.runetek.api.movement.position.Position pos = new org.rspeer.runetek.api.movement.position.Position(c.getSource().getX(), c.getSource().getY(), c.getSource().getZ());
                pos.outline(g);
            }
        }


       /* NodePath path = (NodePath)new AStar().buildPath(new TileNode(Walker2.getLocalPlayerPosition()), new TileNode(3235,3227,0));
        if(path != null){
            for(NodeConnection connection : path.getConnections()){
                if(connection instanceof WalkConnection){
                    renderEvent.getSource().setColor(Color.WHITE);
                }else{
                    renderEvent.getSource().setColor(Color.BLUE);
                }

                Polygon poly = Projection.getTileShape(new Position(connection.getSource().getX(), connection.getSource().getY(), connection.getSource().getZ()));
                if(poly != null){
                    renderEvent.getSource().drawPolygon(poly);
                }
            }


        }

        TileNode tileNode = new TileNode(3230, 3220,0);
        for(NodeConnection connection : tileNode.getConnections()){
            Polygon poly = Projection.getTileShape(new Position(connection.getTarget().getX(), connection.getTarget().getY(), connection.getTarget().getZ()));
            if(poly != null){
                //renderEvent.getSource().drawPolygon(poly);
            }
        }*/
    }
}
