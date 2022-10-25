package npc;

import map.Tile;
import npc.NPC;
import util.Direction;
import util.Movable;
import util.Position;

public class EnemyNPC extends NPC {
    private final int level; //Hook up with player level for balance during map-gen
    private final boolean isMeleeEnemy; //True = melee fighter, false = ranged fighter
    private boolean isDead;

    public EnemyNPC(String name, int level, boolean isMeleeEnemy) {
        super(name, 10*level);
        this.level = level;
        this.isMeleeEnemy = isMeleeEnemy;
    }
    public void die() {
        hp = 0;
        isDead = true;
    }

    public boolean isMeleeEnemy() {
        return isMeleeEnemy;
    }



    public String toString() {
        return "EnemyNPC: \n" + "Name: " + name + " \nLevel: " + level + "\nHP: " + hp;
    }

    @Override
    boolean canMove(Tile tile) {
        return tile.isRoomTile();
    }
}
