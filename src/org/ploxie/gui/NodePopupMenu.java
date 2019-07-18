package org.ploxie.gui;

import org.ploxie.pathfinder.web.node.WebNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodePopupMenu extends JPopupMenu {

    public NodePopupMenu(WorldMapViewer viewer){

        final Font font1 = new Font("Verdana", Font.PLAIN, 10);
        final Insets inset = new Insets(-2,-30,-2,-12);


        JMenuItem info = new JMenuItem(){
            @Override
            public String getText() {
                WebNode hoverNode = viewer.getHoveredNode();
                return super.getText() + ((hoverNode != null) ? hoverNode : "");
            }
        };
        info.setMargin(inset);
        info.setFont(font1);
        info.setEnabled(false);


        JMenuItem removeNode = new JMenuItem("Remove");
        removeNode.setMargin(inset);
        removeNode.setFont(font1);

        removeNode.addActionListener(e -> viewer.removeNode(viewer.getHoveredNode()));


        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!contains(e.getPoint())) {
                    setVisible(false);
                }

                WebNode hoverNode = viewer.getHoveredNode();
                if(hoverNode == null){
                    remove(info);
                    remove(removeNode);
                }else{
                    add(info);
                    add(removeNode);
                }
            }
        };

        viewer.addMouseMotionListener(mouseAdapter);


    }


}
