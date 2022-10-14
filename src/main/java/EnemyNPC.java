import java.util.Arrays;

public class EnemyNPC extends NPC {


    private final int level; //Hook up with player level for balance during map-gen
    private final boolean isMeleeEnemy; //True = melee fighter, false = ranged fighter
    private int attackValue;
    private int defenseValue;
    private int hitPointValue;
    private int experiencePointsWorth;
    private boolean isMapBoss;

    public EnemyNPC(String name, int xPosition, int yPosition, int level, boolean isMeleeEnemy) {
        super(name, xPosition, yPosition);
        this.level = level;
        setEnemyStats(level);
        this.isMeleeEnemy = isMeleeEnemy;
    }

    public EnemyNPC(String name, int xPosition, int yPosition, int level, boolean isMeleeEnemy, boolean isMapBoss) {
        super(name, xPosition, yPosition);
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

    public void approachPlayer(int[] playerPosition) {
        /*
        If player comes within X pixels of enemy
        Then enemy starts walking towards Player and attacks() when in range.
         */
        int[] thisPosition = getCurrentPosition(); //dåligt för memory kanske?
        while (isNotNextToOther(playerPosition)) { //"VILL VI ATT DET SKA KUNNA GÅ DIAGONALT?":
            // Går för tillfället diagonalt, vill att den ska gå ett steg per loop?
            if (playerPosition[0] > thisPosition[0]) {
                moveRight();
            }
            if (playerPosition[0] < thisPosition[0]) {
                moveLeft();
            }
            if (playerPosition[1] > thisPosition[1]) {
                moveUp();
            }
            if (playerPosition[1] < thisPosition[1]) {
                moveDown();
            }
            System.out.println(Arrays.toString(thisPosition));

        }
//            attackPlayer();
    }

    public void attackPlayer() {
        /*
        If player is within Range of enemy, enemy attacks
         */
    }
}
