package org.ploxie.pathfinder.web.node;

public class ItemRetrievedNode extends WebNode {

    private int itemID;
    private int itemCount;

    public ItemRetrievedNode(int x, int y, int z, int itemID, int itemCount) {
        super(x, y, z);

        this.itemID = itemID;
        this.itemCount = itemCount;
    }


    public int getItemID() {
        return itemID;
    }

    public int getItemCount() {
        return itemCount;
    }
}
