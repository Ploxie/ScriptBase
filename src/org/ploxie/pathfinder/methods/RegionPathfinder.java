package org.ploxie.pathfinder.methods;

import org.ploxie.pathfinder.collision.CollisionData;
import org.ploxie.pathfinder.collision.RegionData;
import org.ploxie.pathfinder.wrapper.Position;
import org.ploxie.pathfinder.web.WebNode;

import java.util.HashMap;
import java.util.List;

public class RegionPathfinder extends AStarPathfinder {

    protected static final int STEP_COST = 10;
    protected static final int STEP_DIAGONAL_COST = 14;

    protected HashMap<Position, RegionData> regionLimit;

    public RegionPathfinder(RegionData... regionDataLimit) {
        this.regionLimit = new HashMap<>();
        for (RegionData regionData : regionDataLimit) {
            this.regionLimit.put(regionData.getPosition(), regionData);
        }
    }

    public RegionPathfinder(List<RegionData> regionDataLimit) {
        this.regionLimit = new HashMap<>();
        for (RegionData regionData : regionDataLimit) {
            this.regionLimit.put(regionData.getPosition(), regionData);
        }
    }

    protected RegionData getRegion(WebNode node) {
        return regionLimit.get(node.getRegionPosition());

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
        WebNode targetNode = new WebNode(current.translate(xTranslate, yTranslate,zTranslate));

        if (closedSet.contains(targetNode)) {
            return;
        }

        RegionData currentRegionData = getRegion(current);
        RegionData targetRegionData = getRegion(targetNode);


        if (targetRegionData == null || !targetRegionData.contains(targetNode)) {
            return;
        }

        CollisionData collisionData = currentRegionData.get(current);
        CollisionData targetCollisionData = targetRegionData.get(targetNode);

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
}
