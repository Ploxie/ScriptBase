/*
 * Created by JFormDesigner on Thu Aug 16 14:48:12 CDT 2018
 */

package com.rspeer.ui;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author RSPeer
 */
public class MinerUI extends JFrame {
    public MinerUI() {
        initComponents();
    }

    public JComboBox<String> getSpeedBox() {
        return speedBox;
    }

    public JCheckBoxMenuItem getProjectionCheckbox() {
        return projectionCheckbox;
    }

    public JComboBox<String> getDisposeBox() {
        return disposeBox;
    }

    public JButton getWorkTileButton() {
        return workTileButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - RSPeer
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label3 = new JLabel();
        disposeBox = new JComboBox<>();
        bankDisclaimer = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        separator1 = new JPopupMenu.Separator();
        separator2 = new JPopupMenu.Separator();
        label12 = new JLabel();
        speedBox = new JComboBox<>();
        separator3 = new JPopupMenu.Separator();
        label13 = new JLabel();
        separator4 = new JPopupMenu.Separator();
        workTileButton = new JButton();
        startButton = new JButton();
        separator6 = new JPopupMenu.Separator();
        label14 = new JLabel();
        projectionCheckbox = new JCheckBoxMenuItem();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("RSPeer AIO Miner v0.01");
        contentPane.add(label1);
        label1.setBounds(15, 0, 558, label1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("Created by MadDev");
        label2.setForeground(new Color(255, 51, 51));
        contentPane.add(label2);
        label2.setBounds(15, 25, 512, label2.getPreferredSize().height);

        //---- label4 ----
        label4.setText("Instructions:");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 3f));
        contentPane.add(label4);
        label4.setBounds(15, 60, 512, label4.getPreferredSize().height);

        //---- label5 ----
        label5.setText("1.  Select how you'd like to dispose your ores.");
        contentPane.add(label5);
        label5.setBounds(15, 85, 512, label5.getPreferredSize().height);

        //---- label6 ----
        label6.setText("2. Choose the script speed. The faster the speed, the more CPU it will use.");
        contentPane.add(label6);
        label6.setBounds(15, 120, 512, label6.getPreferredSize().height);

        //---- label7 ----
        label7.setText(" Choose a faster speed if you are having trouble mining a rock over someone else. ");
        contentPane.add(label7);
        label7.setBounds(45, 135, 525, label7.getPreferredSize().height);

        //---- label8 ----
        label8.setText("3. Select the tile you'd like to be positioned on when mining.");
        contentPane.add(label8);
        label8.setBounds(15, 175, 512, label8.getPreferredSize().height);

        //---- label9 ----
        label9.setText("Clicking the button below will use your players current position.");
        contentPane.add(label9);
        label9.setBounds(40, 195, 512, label9.getPreferredSize().height);

        //---- label3 ----
        label3.setText("How would you like to dispose the ores?");
        contentPane.add(label3);
        label3.setBounds(10, 315, 275, label3.getPreferredSize().height);

        //---- disposeBox ----
        disposeBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "M1D1",
            "M2D2",
            "When Full",
            "Bank"
        }));
        contentPane.add(disposeBox);
        disposeBox.setBounds(10, 335, 250, 40);

        //---- bankDisclaimer ----
        bankDisclaimer.setText("Please note: Banking may not work at certain locations.");
        contentPane.add(bankDisclaimer);
        bankDisclaimer.setBounds(10, 380, 395, bankDisclaimer.getPreferredSize().height);

        //---- label10 ----
        label10.setText("4. Hover over the rocks you'd like to mine and click them.");
        contentPane.add(label10);
        label10.setBounds(15, 240, 512, 16);

        //---- label11 ----
        label11.setText("They will turn green when you click them, signaling that they are selected.");
        contentPane.add(label11);
        label11.setBounds(40, 260, 512, 16);
        contentPane.add(separator1);
        separator1.setBounds(5, 305, 575, 20);
        contentPane.add(separator2);
        separator2.setBounds(5, 45, 575, 20);

        //---- label12 ----
        label12.setText("Select a script speed.");
        contentPane.add(label12);
        label12.setBounds(10, 420, 275, 16);

        //---- speedBox ----
        speedBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "Insane",
            "Fast",
            "Medium",
            "Slow"
        }));
        contentPane.add(speedBox);
        speedBox.setBounds(10, 440, 250, 30);
        contentPane.add(separator3);
        separator3.setBounds(5, 400, 575, 15);

        //---- label13 ----
        label13.setText("Set your work tile. (The tile you will be mining from.)");
        contentPane.add(label13);
        label13.setBounds(10, 500, 555, 16);
        contentPane.add(separator4);
        separator4.setBounds(10, 480, 575, 20);

        //---- workTileButton ----
        workTileButton.setText("Set Work Tile To Players Location");
        contentPane.add(workTileButton);
        workTileButton.setBounds(10, 525, 320, 40);

        //---- startButton ----
        startButton.setText("Start Mining");
        contentPane.add(startButton);
        startButton.setBounds(30, 630, 525, 50);
        contentPane.add(separator6);
        separator6.setBounds(5, 575, 575, 20);

        //---- label14 ----
        label14.setText("To remove a rock, just click it again if it is is selected.");
        contentPane.add(label14);
        label14.setBounds(40, 280, 512, 16);

        //---- projectionCheckbox ----
        projectionCheckbox.setText("Enable low CPU mode after start.");
        contentPane.add(projectionCheckbox);
        projectionCheckbox.setBounds(new Rectangle(new Point(10, 595), projectionCheckbox.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(590, 725));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - RSPeer
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label3;
    private JComboBox<String> disposeBox;
    private JLabel bankDisclaimer;
    private JLabel label10;
    private JLabel label11;
    private JPopupMenu.Separator separator1;
    private JPopupMenu.Separator separator2;
    private JLabel label12;
    private JComboBox<String> speedBox;
    private JPopupMenu.Separator separator3;
    private JLabel label13;
    private JPopupMenu.Separator separator4;
    private JButton workTileButton;
    private JButton startButton;
    private JPopupMenu.Separator separator6;
    private JLabel label14;
    private JCheckBoxMenuItem projectionCheckbox;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
