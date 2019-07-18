package org.ploxie.api.rspeer.scripts.quester.cooksassistant;

import org.ploxie.api.rspeer.scripts.quester.Quest;
import org.ploxie.common.ItemRetriever;
import org.ploxie.pathfinder.Walker;
import org.ploxie.pathfinder.web.Web;
import org.rspeer.runetek.adapter.Varpbit;
import org.rspeer.runetek.api.Varps;

public class CooksAssistant extends Quest {

    public static final int SETTING = 29;
    private static final int EGG_ID = 1944;
    private static final int BUCKET_MILK_ID = 1927;
    private static final int POT_FLOUR_ID = 1933;

    @Override
    public boolean isComplete() {
        return Varps.get(SETTING) == 2;
    }

    @Override
    public boolean hasRequirements() {
        return true;
    }

    @Override
    public boolean execute() {
        boolean getEgg = ItemRetriever.retrieveItem(EGG_ID, 1);

        return false;
    }
}
