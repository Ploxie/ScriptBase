package com.rspeer;

import com.rspeer.framework.Action;
import com.rspeer.framework.ActionStatus;
import com.rspeer.framework.Executor;
import com.rspeer.settings.DropType;
import org.rspeer.RSPeer;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.path.Path;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.providers.RSTile;
import org.rspeer.ui.Log;

import javax.swing.*;
import java.util.function.Predicate;

public class MineHelper {

    public static boolean isMining() {
        return Players.getLocal().getAnimation() != -1;
    }

    private static Predicate<SceneObject> rock = rock -> {
        if(rock == null)
            return false;
        short[] colors = rock.getDefinition().getNewColors();
        if (colors == null || colors.length == 0)
            return false;
        if(!rock.getName().equals("Rocks"))
            return false;
        return Settings.getSelectedRocks().contains(rock.getPosition());
    };

    private static SceneObject getNextRock() {
        try {
            return SceneObjects.getNearest(rock);
        } catch (Exception e) {
            return null;
        }
    }

    public static Action animating() {
        return () -> {
            if(Players.getLocal().getAnimation() == -1) {
                return ActionStatus.SUCCESS;
            }
            Position cached = Store.getCurrentRock();
            if(cached == null) {
                return ActionStatus.SUCCESS;
            }
            final SceneObject obj = SceneObjects.getFirstAt(cached);
            if(obj == null || obj.getDefinition() == null) {
                Store.setCurrentRock(null);
                Executor.executeTillStopBackground("MINE_ROCK", 10, MineHelper.mineNextRock());
                return ActionStatus.SUCCESS;
            }
            short[] colors = obj.getDefinition().getNewColors();
            if(colors == null || colors.length == 0) {
                Store.setCurrentRock(null);
                Executor.executeTillStopBackground("MINE_ROCK", 10, MineHelper.mineNextRock());
                return ActionStatus.RUNNING;
            }
            return ActionStatus.RUNNING;
        };
    }

    public static Action mineNextRock() {
        return () -> {
            if(Settings.getDropType() == DropType.M1D1 && Inventory.contains(MineHelper.ORE)) {
                return dropOre().execute();
            }
            if (Settings.getDropType() == DropType.M2D2 && Inventory.getCount(MineHelper.ORE) >= 2) {
               return dropOre().execute();
            }
            if(Inventory.isFull()) {
                return ActionStatus.SUCCESS;
            }
            if(Store.getCurrentRock() != null && isMining()) {
                return ActionStatus.SUCCESS;
            }
            SceneObject next;
            Position cached = Store.getCurrentRock();
            if(cached != null) {
                next = SceneObjects.getFirstAt(cached);
            } else {
                next = getNextRock();
            }
            if(next == null && cached != null) {
                Store.setCurrentRock(null);
                return ActionStatus.RUNNING;
            }
            if(next == null) {
                Store.setCurrentRock(null);
                return ActionStatus.RUNNING;
            }
            short[] colors = next.getDefinition().getNewColors();
            if(colors == null || colors.length == 0) {
                Store.setCurrentRock(null);
                return ActionStatus.RUNNING;
            }
            Store.setCurrentRock(next.getPosition());
            if (!Players.getLocal().isMoving()) {
                next.interact("Mine");
                Time.sleep(150, 450);
                Time.sleepUntil(MineHelper::isMining, 1200);
            }
            return ActionStatus.RUNNING;
        };
    }

    public static Action getBackToWorkArea() {
        return () -> {
            Position workTile = Settings.getWorkTile();
            if(workTile.equals(Players.getLocal().getPosition())) {
                return ActionStatus.SUCCESS;
            }

            if(Movement.getRunEnergy() > Settings.getRunEnergyToggle() && !Movement.isRunEnabled()) {
                Movement.toggleRun(true);
                Settings.setRunEnergyToggle(Random.nextInt(45, 65));
            }
            if(Movement.isDestinationSet() && Movement.getDestinationDistance() >= 3  && Movement.getDestinationDistance() < 100){
                return ActionStatus.RUNNING;
            }

            if(Players.getLocal().getPosition().distance(new Position(3032, 9738,0)) > 100){
                SceneObject ladder = SceneObjects.getNearest(sceneObject -> sceneObject.distance(new Position(3021, 3339, 0)) <= 3);
                if(ladder != null){
                    Log.info("Going down ladder");
                    ladder.interact("Climb-down");
                }else{
                    Log.info("Cant find ladder down");
                }

                return ActionStatus.RUNNING;
            }else{
                Path p = PathHelper.tryGetPath(workTile, 15);
                if(p == null) {
                    JOptionPane.showMessageDialog(RSPeer.getClient().getCanvas(), "Unable to build a path back to mining area after 15 tries. Stopping script.");
                    Miner.getInstance().onStop();
                    return ActionStatus.KILL;
                }
                p.walk();
            }


            Time.sleep(100, 550);
            return ActionStatus.RUNNING;
        };
    }

    public static Action depositOre() {
        return () -> {
            if(!Inventory.contains(s -> s.getName().contains("ore") || s.getName().contains("Coal"))) {
                return ActionStatus.SUCCESS;
            }
            if(Bank.isOpen()) {
                Bank.depositAllExcept(s -> s.getName().contains("pickaxe"));
                return ActionStatus.RUNNING;
            }
            if(Movement.getRunEnergy() > Settings.getRunEnergyToggle() && !Movement.isRunEnabled()) {
                Movement.toggleRun(true);
                Settings.setRunEnergyToggle(Random.nextInt(45, 65));
            }
            if(Movement.isDestinationSet() && Movement.getDestinationDistance() >= 3 && Movement.getDestinationDistance() < 100){
                return ActionStatus.RUNNING;
            }

            if(Players.getLocal().getPosition().distance(new Position(3032, 9738,0)) <= 100){
                SceneObject ladder = SceneObjects.getNearest("Ladder");
                if(ladder != null){
                    Log.info("Going up ladder");
                    ladder.interact("Climb-up");
                }else{
                    Log.info("Cant find ladder up");
                }

                return ActionStatus.RUNNING;
            }

            Bank.open();
            return ActionStatus.RUNNING;
        };
    }




    public static final Predicate<Item> ORE = item -> item.getName().endsWith("ore") || item.getName().startsWith("Uncut") || item.getName().endsWith("Coal");

    public static Action dropOre() {
       return () -> {
           Item[] i = Inventory.getItems(ORE);
           if(i.length == 0) {
               return ActionStatus.SUCCESS;
           }
           for (Item item : i) {
               item.interact("Drop");
               Time.sleep(150, 500);
           }
           return ActionStatus.RUNNING;
       };
    }

}
