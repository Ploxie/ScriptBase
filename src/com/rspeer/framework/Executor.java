package com.rspeer.framework;

import com.rspeer.Miner;
import com.rspeer.Settings;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;

import java.util.concurrent.Executors;

public class Executor {

    private static String CURRENT_TASK;
    private static int CURRENT_TASK_PRIORITY;
    private static boolean shouldStop;

    public static String getCurrentTask()
    {
        return CURRENT_TASK;
    }

    public static void executeTillStop(String name, int priority, Action action) {
        if(CURRENT_TASK != null && CURRENT_TASK.equals(name))
            return;
        if(priority < CURRENT_TASK_PRIORITY)
            return;
        CURRENT_TASK = name;
        CURRENT_TASK_PRIORITY = priority;
        while (action.execute() == ActionStatus.RUNNING) {
            if(CURRENT_TASK != null && !CURRENT_TASK.equals(name)) {
                break;
            }
            if(shouldStop) {
                System.out.println("Stopping loop due to script stop.");
                break;
            }
            Miner instance = Miner.getInstance();
            if(instance == null) {
                System.out.println("Instance is null, breaking.");
                break;
            }
            if(instance.isPaused() || !Game.isLoggedIn()) {
                System.out.println("Script is paused or logged out, breaking loop.");
                break;
            }
            Time.sleep(Settings.getLoopReturn());
        }
        CURRENT_TASK_PRIORITY = -1;
        CURRENT_TASK = null;
    }

    public static void executeTillStopBackground(String name, int priority, Action action) {
        if(CURRENT_TASK != null && CURRENT_TASK.equals(name))
            return;
        Executors.newSingleThreadExecutor().execute(() -> executeTillStop(name, priority, action));
    }

    public static void setShouldStop(boolean shouldStop) {
        Executor.shouldStop = shouldStop;
    }
}
