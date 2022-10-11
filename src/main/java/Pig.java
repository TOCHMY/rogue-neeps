class Pig extends Enemy implements Killable, Movement, Positionable {

    private int xPos;
    private int yPos;
    private final int xp;
    private int health;

    private boolean isDead;

    public Pig(){
        this.xPos = 0;
        this.yPos = 0;
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

    @Override
    public void moveUp() {
        yPos += 10;
    }

    @Override
    public void moveDown() {
        yPos -= 10;
    }

    @Override
    public void moveRight() {
        xPos += 10;
    }

    @Override
    public void moveLeft() {
        xPos -= 10;
    }

    @Override
    public int getCurrentX() {
        return xPos;
    }

    @Override
    public int getCurrentY() {
        return yPos;
    }
}
