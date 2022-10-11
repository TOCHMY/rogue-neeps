class Pig extends Enemy implements Killable {

    private final int xp;
    private int health;

    private boolean isDead;

    public Pig(){
        this.xp = 20;
        this.health = 10;
        this.isDead = false;
    }


    @Override
    int getXp() {
        return this.xp;
    }

    @Override
    int getHealth() {
        return this.health;
    }



    @Override
    public void takeDmg( Player p, int amount) {
        health -= amount;
        if(health <= 0){
            health = 0;
            p.addXp(xp);
            die();
        }
    }

    @Override
    public void die() {
        this.isDead = true;
    }
}
