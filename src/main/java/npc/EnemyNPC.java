package npc;

import npc.NPC;

public class EnemyNPC extends NPC {


    private final int level; //Hook up with player level for balance during map-gen
    private final boolean isMeleeEnemy; //True = melee fighter, false = ranged fighter
    private String name;
    private int attackValue;
    private int defenseValue;
    private int hitPointValue;
    private int experiencePointsWorth;
    private boolean isMapBoss;
    private boolean isDead;

    public EnemyNPC(String name, int level, boolean isMeleeEnemy) {
        this.name = name;
        this.level = level;
        setEnemyStats(level);
        this.isMeleeEnemy = isMeleeEnemy;
    }

    public EnemyNPC(String name, int level, boolean isMeleeEnemy, boolean isMapBoss) {
        this.name = name;
        this.level = level;
        setEnemyStats(level * 10); //+ or * a static value to make it stronger? Depending on the player level algorithm thingy
        this.isMeleeEnemy = isMeleeEnemy;
        this.isMapBoss = isMapBoss;
    }

    //Numbers used are arbitrary for now, needs adjusting for balance purpose.
    private void setEnemyStats(int level) {
        attackValue = level;
        defenseValue = level;
        hitPointValue = 10 * level;
        experiencePointsWorth = level * 2;
    }


    public void attackPlayer() {
        /*
        If player is within Range of enemy, enemy attacks
         */
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
}
