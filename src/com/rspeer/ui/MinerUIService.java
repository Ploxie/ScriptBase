package com.rspeer.ui;

import com.rspeer.Settings;
import com.rspeer.settings.DropType;
import com.rspeer.settings.Speed;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.ui.component.BotTitlePane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MinerUIService {

    public static void initialize(MinerUI ui) {
        ui.getStartButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                setSettingsAndDipose(ui);
                super.mouseReleased(e);
            }
        });
        ui.getWorkTileButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Settings.setWorkTile(Players.getLocal().getPosition());
                ui.getWorkTileButton().setText(Settings.getWorkTile().toString());
                super.mousePressed(e);
            }
        });
        BotTitlePane.decorate(ui);
        ui.setVisible(true);
    }

    private static void setSettingsAndDipose(MinerUI ui) {
        Object speed = ui.getSpeedBox().getSelectedItem();
        if(speed == null) speed = Speed.FAST;
        Settings.setSpeed(Speed.fromText(speed.toString()));
        Object dropType = ui.getDisposeBox().getSelectedItem();
        if(dropType == null) dropType = DropType.WHEN_FULL;
        Settings.setDropType(DropType.fromText(dropType.toString()));
        if(Settings.getWorkTile() == null) {
            Settings.setWorkTile(Players.getLocal().getPosition());
        }
        if(Settings.getSelectedRocks().size() == 0) {
            ui.getStartButton().setText("Please select rocks to mine by clicking on them!");
            return;
        }
        if(ui.getProjectionCheckbox().isSelected()) {
            Projection.setLowCPUMode(true);
        }
        Settings.setTotalRocksSelected(Settings.getSelectedRocks().size());
        Settings.setIsSetup(true);
        ui.dispose();
    }

}
