class Pig extends EnemyNPC implements Killable, Movement {

    private String name;
    private final int xp;
    private int health;

    private boolean isDead;

    public Pig(){
        super("Pig", 1, true);
        this.xp = 20;
        this.health = 10;
        this.isDead = false;
    }


/*    public Pig(int xPos, int yPos){
        this.xp = 20;
        this.health = 10;
        this.isDead = false;
    }*/





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
/*        if(questArrayList.get(1).isInitiated) {
            setPigsKilled();
        }*/
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
