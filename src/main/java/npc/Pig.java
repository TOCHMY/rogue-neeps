package npc;

import player.Player;
import util.Killable;
import util.Movement;

public class Pig extends EnemyNPC implements Killable, Movement {
    private final int xp;
    private int hitPointValue;

    private boolean isDead;

    public Pig(){
        super("Pig", 1, true);
        this.xp = 20;
        this.hitPointValue = 10;
        this.isDead = false;
    }

    @Override
    public void takeDmg(Player p, int amount) {
        hitPointValue -= amount;
        if(hitPointValue <= 0){
            hitPointValue = 0;
            p.addXp(xp);
            die();
        }
    }

    @Override
    public void die() {
        hitPointValue = 0;
        isDead = true;
    }

    @Override
    public int getXP() {
        return this.xp;
    }

    @Override
    public int getHP() {
        return this.hitPointValue;
    }

    // Definiera movement speed i konstruktor via npc.NPC klass eller npc.EnemyNPC klass
    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveLeft() {

    }


}