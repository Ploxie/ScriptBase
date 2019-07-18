package org.ploxie.pathfinder.web.webbank;

import org.ploxie.pathfinder.methods.astar.AStarEndCondition;
import org.ploxie.pathfinder.web.node.Node;
import org.ploxie.wrapper.Position;
import org.ploxie.wrapper.Positionable;

public enum WebBank implements Positionable {
    LUMBRIDGE_CASTLE(1,        2,   3),
    DRAYNOR_VILLAGE (3092,  3244,   0),
    EDGEVILLE       (3093,  3494,   0),
    VARROCK_WEST    (3182,  3441,   0),
    VARROCK_EAST    (3253,  3421,   0),
    GRAND_EXCHANGE  (3164,  3486,   0);


    private Position position;

    WebBank(int x, int y, int z){
        this.position = new Position(x,y,z);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public static WebBank getBank(Positionable position){
        for(WebBank bank : values()){
            if(bank.getPosition().equals(position.getPosition())){
                return bank;
            }
        }
        return null;
    }

    public static class WebBankCondition implements AStarEndCondition {

        @Override
        public boolean validate(Node node) {
            return node instanceof WebBankNode;
        }
    }

}
