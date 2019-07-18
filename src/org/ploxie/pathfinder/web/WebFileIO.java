package org.ploxie.pathfinder.web;


import org.ploxie.pathfinder.web.connections.NodeConnection;
import org.ploxie.pathfinder.web.connections.NodeWalkConnection;
import org.ploxie.pathfinder.web.node.WebNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WebFileIO {

    public static void saveWebToFile(Web web, File file){
        List<NodeConnection> addedConnectionsList = new ArrayList<>();

        for(WebNode node : web){
            for(NodeConnection connection : node.getConnections()){
                NodeConnection reversed = new NodeWalkConnection(connection.getTarget(), connection.getSource());
                if(addedConnectionsList.contains(connection) || addedConnectionsList.contains(reversed)){
                    continue;
                }
                addedConnectionsList.add(connection);
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(NodeConnection connection : addedConnectionsList){
                writer.write(connection.toString()+"\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Web loadWeb(File file){
        Web web = new Web();

        int connectionsLoaded = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                String[] nodes = line.split(" -> ");
                String firstNode = nodes[0].replaceAll("\\(", "").replaceAll("\\)", "");
                String secondNode = nodes[1].replaceAll("\\(", "").replaceAll("\\)", "");

                String[] firstNodeAttributes = firstNode.split(", ");
                String[] secondNodeAttributes = secondNode.split(", ");

                org.ploxie.pathfinder.web.node.WebNode source = web.getNode(Integer.parseInt(firstNodeAttributes[0]), Integer.parseInt(firstNodeAttributes[1]), Integer.parseInt(firstNodeAttributes[2]));
                WebNode target = web.getNode(Integer.parseInt(secondNodeAttributes[0]), Integer.parseInt(secondNodeAttributes[1]), Integer.parseInt(secondNodeAttributes[2]));
                web.addConnection(source, target);
                connectionsLoaded++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Loaded "+connectionsLoaded +" connections and "+web.size()+" nodes!");

        return web;
    }

}
