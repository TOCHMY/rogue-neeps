package npc;

import map.Tile;
import npc.NPC;
import player.Player;
import util.Direction;
import util.Killable;
import util.Movable;
import util.Position;

public class EnemyNPC extends NPC implements Killable {
    private final int level; //Hook up with player level for balance during map-gen
    private final boolean isMeleeEnemy; //True = melee fighter, false = ranged fighter
    private boolean isDead; // Might be used to check if it should be removed from Map
    private int xp;

    public EnemyNPC(String name, int level, boolean isMeleeEnemy, int xp, int hp) {
        super(name, hp);
        this.level = level;
        this.isMeleeEnemy = isMeleeEnemy;
        this.xp = xp;
    }
    public EnemyNPC(String name, int level, boolean isMeleeEnemy) {
        super(name, 20);
        this.level = level;
        this.isMeleeEnemy = isMeleeEnemy;
    }

    @Override
    public void takeDmg(Player p, double amount) {
        hp -= amount;
        if (hp <= 0) {
            hp = 0;
            p.addXp(xp);
            die();
        }
    }

    @Override
    public void die() {
        hp = 0;
        isDead = true;
    }

    @Override
    public int getXP() {
        return this.xp;
    }

    @Override
    public double getHP() {
        return this.hp;
    }

    public boolean isMeleeEnemy() {
        return isMeleeEnemy;
    }

    public boolean isDead() {
        return isDead;
    }

    @Override
    public boolean canMove(Tile tile) {
        return tile.isRoomTile();
    }

    public String toString() {
        return "EnemyNPC:\n" + "Name: " + name + "\nLevel: " + level + "\nHP: " + hp;
    }
}
