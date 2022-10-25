package npc;

import util.Direction;

public abstract class NPC  {
    private Direction npcFacingDirection;

    public NPC() {
    }

    //IMplement movement

    public void move(Direction dir){
        setNPCFacingDirection(dir);
    }


    private void setNPCFacingDirection(Direction direction) {
        npcFacingDirection = direction;
    }

}