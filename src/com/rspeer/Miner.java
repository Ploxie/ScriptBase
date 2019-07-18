package com.rspeer;

import com.rspeer.framework.Executor;
import com.rspeer.settings.DropType;
import com.rspeer.ui.MinerUI;
import com.rspeer.ui.MinerUIService;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.menu.ContextMenu;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.Projection;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.event.listeners.ItemTableListener;
import org.rspeer.runetek.event.listeners.MouseInputListener;
import org.rspeer.runetek.event.listeners.ObjectSpawnListener;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.ItemTableEvent;
import org.rspeer.runetek.event.types.ObjectSpawnEvent;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.runetek.providers.subclass.GameCanvas;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@ScriptMeta(developer = "MadDev", desc = "A simple power miner. Mines and drops ores.", name = "RSPeer Power Miner")
public class Miner extends Script implements RenderListener, ObjectSpawnListener, ItemTableListener, MouseInputListener {

    private ScheduledFuture<?> perMinuteExecutor;
    private MinerUI settings;

    private static Miner instance;

    public static Miner getInstance() {
        return instance;
    }

    @Override
    public void onStart() {
        super.onStart();
        instance = this;
        settings = new MinerUI();
        MinerUIService.initialize(settings);
        perMinuteExecutor = Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(Stats::incrementRunTime, 1, 1, TimeUnit.MINUTES);
        GameCanvas.setInputEnabled(true);
    }

    @Override
    public int loop() {
        if (!Settings.isSetup()) {
            if (!GameCanvas.isInputEnabled()) {
                GameCanvas.setInputEnabled(true);
            }
            return Settings.getLoopReturn();
        }
        if(isPaused() || !Game.isLoggedIn()) {
            return Settings.getLoopReturn();
        }
        if (Inventory.isFull()) {
            Executor.executeTillStop("CLEARING_ORE", 4,
                    Settings.getDropType() == DropType.WHEN_FULL
                            ? MineHelper.dropOre() : MineHelper.depositOre());

            return Settings.getLoopReturn();
        }
        if (MineHelper.isMining()) {
            Executor.executeTillStop("MINING", 3, MineHelper.animating());
        }
        if (Settings.getWorkTile() != null && Settings.getWorkTile().distance(Players.getLocal().getPosition()) > 25) {
            Executor.executeTillStop("WALK_TO_MINE", 5, MineHelper.getBackToWorkArea());
        }
        if (Store.getCurrentRock() == null || !MineHelper.isMining()) {
            Executor.executeTillStop("MINE_ROCK", 1, MineHelper.mineNextRock());
        }

        return Settings.getLoopReturn();
    }

    @Override
    public void notify(RenderEvent e) {
        Graphics g = e.getSource();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int y = 35;
        int x = 10;
        if (!Settings.isSetup()) {
            g2.drawString("RSPeer Miner", x, y);
            g2.drawString("Waiting for settings.", x, y += 15);
            g2.drawString("Selected Rocks: " + Settings.getTotalRocksSelected(), x, y + 15);

            for (Position selectedRock : Settings.getSelectedRocks()) {
                g2.setColor(Color.green);
                selectedRock.getPosition().outline(g2);
            }
            g2.setColor(Color.orange);
            long[] hovered = ContextMenu.getOnCursorUids();
            for (long l : hovered) {
                SceneObject o = SceneObjects.getByUid(l);
                if (o == null)
                    continue;
                o.getPosition().outline(g2);
            }
            return;
        }
        y = 35;
        String task = Executor.getCurrentTask();
        g2.setColor(Color.PINK);
        g2.drawString("RSPeer Miner", x, y);
        g2.drawString("Minutes Elapsed: " + Stats.getMinuteRunTime(), x, y += 20);
        g2.drawString("Task: " + (task == null ? "MINE_ROCK" : task), x, y += 20);
        g2.drawString("Dispose Type: " + Settings.getDropType().name(), x, y += 20);
        g2.drawString("Speed: " + Settings.getSpeed().name(), x, y += 20);
        Position p = Store.getCurrentRock();
        g2.drawString("Rock: " + (p != null ? p.getX() + ", " + p.getY() : "None"), x, y += 20);
        g2.drawString("Ore Mined: " + Stats.getOreMined(), x, y += 20);
        g2.drawString("Selected Rocks: " + Settings.getTotalRocksSelected(), x, y += 20);
        if(p != null) {
            p.outline(g2);
        }
    }

    @Override
    public void onStop() {
        Executor.setShouldStop(true);
        if (settings != null) {
            settings.dispose();
        }
        if (perMinuteExecutor != null) {
            perMinuteExecutor.cancel(true);
        }
        Projection.setLowCPUMode(false);
        Log.fine("Thank you for using RSPeer AIO Miner, created by MadDev.");
        super.onStop();
    }

    @Override
    public void notify(ObjectSpawnEvent e) {
        Position current = Store.getCurrentRock();
        if (current == null)
            return;
        //Our rock was mined out.
        if (e.getPosition().equals(current)) {
            Store.setCurrentRock(null);
            Executor.executeTillStopBackground("MINE_ROCK", 10, MineHelper.mineNextRock());
        }
    }

    @Override
    public void notify(ItemTableEvent e) {
        if (e.getChangeType() == ItemTableEvent.ChangeType.ITEM_ADDED && e.getId() != -1) {
            Stats.setOreMined(Stats.getOreMined() + 1);
        }
    }


    @Override
    public void notify(MouseEvent e) {
        if (Settings.isSetup()) {
            return;
        }
        if (e.getButton() == 1) {
            long[] hovered = ContextMenu.getOnCursorUids();
            for (long l : hovered) {
                SceneObject o = SceneObjects.getByUid(l);
                if (o != null) {
                    Position p = o.getPosition();
                    if (Settings.getSelectedRocks().contains(p)) {
                        Settings.getSelectedRocks().remove(p);
                    } else {
                        Settings.appendSelectedRock(o.getPosition());
                    }
                    Settings.setTotalRocksSelected(Settings.getSelectedRocks().size());
                }
            }
        }
    }
}
