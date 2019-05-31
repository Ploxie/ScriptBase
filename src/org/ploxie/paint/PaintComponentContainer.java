package org.ploxie.paint;

import java.util.ArrayList;
import java.util.List;

public abstract class PaintComponentContainer {

    private List<PaintComponent> children = new ArrayList<>();

    public void add(PaintComponent component){
        getChildren().add(component);
    }

    public List<PaintComponent> getChildren() {
        return children;
    }

}
