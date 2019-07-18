package org.ploxie.api.rspeer.scripts.quester;

import org.ploxie.tasksystem.Task;

public abstract class Quest implements Task {


    public abstract boolean isComplete();

    public abstract boolean hasRequirements();



}
