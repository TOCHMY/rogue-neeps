package npc;

import map.Map;
import util.Direction;
import util.Movement;

public abstract class NPC implements Movement {
    private Direction npcFacingDirection;

    public NPC() {
    }

    //IMplement movement
    @Override
    public void moveUp() {
        setNPCFacingDirection(Direction.UP);
    }

    @Override
    public void moveDown() {
        setNPCFacingDirection(Direction.DOWN);
    }

    @Override
    public void moveRight() {
        setNPCFacingDirection(Direction.RIGHT);
    }

    @Override
    public void moveLeft() {
        setNPCFacingDirection(Direction.LEFT);
    }

    private void setNPCFacingDirection(Direction direction) {
        npcFacingDirection = direction;
    }

}