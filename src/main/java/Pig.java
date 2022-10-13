class Pig implements Killable, Movement, Positionable {
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


    public Pig(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.xp = 20;
        this.health = 10;
        this.isDead = false;
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
        health = 0;
        isDead = true;
    }

    @Override
    public int getXP() {
        return this.xp;
    }

    @Override
    public int getHP() {
        return this.health;
    }

    // Definiera movement speed i konstruktor via NPC klass eller EnemyNPC klass
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
