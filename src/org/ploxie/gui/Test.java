package org.ploxie.gui;

import org.ploxie.paint.PaintManager;
import org.ploxie.paint.components.PaintLabel;
import org.ploxie.paint.components.PaintPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Test extends JFrame {

    public Test(){
        setSize(800, 600);

        PaintPanel paintPanel = new PaintPanel(0,0,200,200);
        PaintPanel paintPanel2 = new PaintPanel( 10, 10, 50,50);
        PaintLabel label = new PaintLabel("Nigger");
        label.setPosition(100,100);

        paintPanel.add(paintPanel2);
        paintPanel.add(label);
        PaintManager.addComponent(paintPanel);

        JPanel panel = new JPanel(){

            @Override
            public void paint(Graphics g) {
                g.setColor(Color.black);
                g.fillRect(0,0,800,600);

                PaintManager.paint(g);
            }

        };

        add(panel);
    }

    public static void main(String[] args) {
        Test test = new Test();


       test.setVisible(true);
    }
}
