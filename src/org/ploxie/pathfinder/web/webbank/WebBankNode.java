package org.ploxie.pathfinder.web.webbank;

import org.ploxie.pathfinder.web.node.WebNode;
import org.ploxie.wrapper.Position;

public class WebBankNode extends WebNode {

    public WebBankNode(int x, int y, int z) {
        super(x, y, z);
    }

    public WebBankNode(Position position) {
        super(position);
    }

}
