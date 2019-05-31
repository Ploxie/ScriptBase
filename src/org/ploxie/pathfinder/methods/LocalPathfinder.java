package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.collision.CollisionData;
import org.ploxie.pathfinder.collision.LocalRegion;
import org.ploxie.pathfinder.collision.Region;
import org.ploxie.pathfinder.web.WebNode;
import org.ploxie.pathfinder.web.path.LocalPath;
import org.ploxie.pathfinder.web.path.WebPath;

public class LocalPathfinder extends AStarPathfinder {

    protected static final int STEP_COST = 10;
    protected static final int STEP_DIAGONAL_COST = 14;

    private LocalRegion localRegion;

    public LocalPathfinder(Region localRegion) {
        //this.localRegion = localRegion;
    }

    @Override
    protected void addNeighbours(WebNode node) {
        addNeighbour(node, 0, 1, 0, STEP_COST);
        addNeighbour(node, 0, -1, 0, STEP_COST);
        addNeighbour(node, 1, 0, 0, STEP_COST);
        addNeighbour(node, -1, 0, 0, STEP_COST);

        addNeighbour(node, -1, 1, 0, STEP_DIAGONAL_COST);
        addNeighbour(node, 1, 1, 0, STEP_DIAGONAL_COST);
        addNeighbour(node, -1, -1, 0, STEP_DIAGONAL_COST);
        addNeighbour(node, 1, -1, 0, STEP_DIAGONAL_COST);
    }

    private void addNeighbour(WebNode current, int xTranslate, int yTranslate, int zTranslate, int cost) {
        WebNode targetNode = new WebNode(current.translate(xTranslate, yTranslate, zTranslate));

        if (closedSet.contains(targetNode)) {
            return;
        }

        CollisionData collisionData = localRegion.get(current);
        CollisionData targetCollisionData = localRegion.get(targetNode);

        if (collisionData == null || targetCollisionData == null) {
            return;
        }

        if (!targetCollisionData.isWalkable() || collisionData.blockedInDirection(xTranslate, yTranslate)) {
            return;
        }

        if (!openList.contains(targetNode)) {
            double g = current.getGCost() + cost;
            targetNode.calculateHeuristic(end);

            targetNode.setParent(current);
            targetNode.setGCost(g);

            openList.add(targetNode);
            return;
        }
    }

    @Override
    protected WebPath backtracePath(WebNode node) {
        LocalPath path = new LocalPath();
        path.add(node);
        WebNode parent;
        while ((parent = node.getParent()) != null) {
            path.add(0, parent);
            node = parent;
        }
        return path;
    }

}
