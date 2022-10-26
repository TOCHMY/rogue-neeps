package npc;

import player.Player;
import util.Killable;

public class Pig extends EnemyNPC implements Killable {
    private final int xp;
    private double hitPointValue;

    private boolean isDead;

    public Pig(){
        super("Pig", 1, true);
        this.xp = 20;
        this.hitPointValue = 10;
        this.isDead = false;
    }

    @Override
    public void takeDmg(Player p, double amount) {
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
    public double getHP() {
        return this.hitPointValue;
    }


}
