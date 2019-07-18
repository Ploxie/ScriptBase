package org.ploxie.tasksystem;

public abstract class TaskNode {

    protected Task task;

    public boolean execute(){
        return task.execute();
    }



}
