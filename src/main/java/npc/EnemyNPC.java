package npc;

import npc.NPC;

public class EnemyNPC extends NPC {


    private final int level; //Hook up with player level for balance during map-gen
    private final boolean isMeleeEnemy; //True = melee fighter, false = ranged fighter
    private final String name;
    private int hitPointValue;
    private boolean isDead;

    public EnemyNPC(String name, int level, boolean isMeleeEnemy) {
        this.name = name;
        this.level = level;
        setEnemyStats(level);
        this.isMeleeEnemy = isMeleeEnemy;
    }

    //Numbers used are arbitrary for now, needs adjusting for balance purpose.
    private void setEnemyStats(int level) {
//        attackValue = level;
//        defenseValue = level;
        hitPointValue = 10 * level;
    }


    public void die() {
        hitPointValue = 0;
        isDead = true;
    }

    public boolean isMeleeEnemy() {
        return isMeleeEnemy;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "EnemyNPC: \n" + "Name: " + name + " \nLevel: " + level + "\nHP: " + hitPointValue;
    }
}
