import java.util.Random;

public class NPC extends Quest implements Movement {

    //Set NPC movement range? <- så typ en specifik NPC kan inte röra sig längre än 2 pixlar
    // från sin initialPosition.
    private final String name;
    private int xPosition;
    private int yPosition;
    private final int[] currentPosition = new int[2]; //ABC

    //tanken är att initialPosition ska användas för att se till att NPC inte vandrar för långt bort på kartan
    private int[] initialPosition = new int[2];

    public NPC(String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        initialPosition[0] = xPosition;
        initialPosition[1] = yPosition;
        setCurrentPosition(xPosition, yPosition);
    }

    public NPC(String name) {
        this.name = name;
    }

    private void setCurrentPosition(int xPosition, int yPosition) {
        currentPosition[0] = xPosition;
        currentPosition[1] = yPosition;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    private int generateRandomMovementNumber() {
        Random rand = new Random();
        return rand.nextInt(4) + 1;
    }

    private int generateRandomWaitNumber() {
        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }

    //Testa alla move-metoder så att NPC inte kolliderar med terrain
    public void moveUp() {
        yPosition++;
        setCurrentPosition(xPosition, yPosition);
    }

    public void moveDown() {
        yPosition--;
        setCurrentPosition(xPosition, yPosition);
    }

    public void moveRight() {
        xPosition++;
        setCurrentPosition(xPosition, yPosition);
    }

    public void moveLeft() {
        xPosition--;
        setCurrentPosition(xPosition, yPosition);
    }

    //When an NPC is idle, meaning not interacting with the Player in any way, the NPC has the ability to
    // walk around randomly on the map.
    public void idleMove() {
        int movementDirection = generateRandomMovementNumber();
        int wait = generateRandomWaitNumber();
        //To prevent NPCs wandering around in lightning speed, wait for a randomly generated amount of seconds
//            TimeUnit.SECONDS.wait(wait); <-- funkar ej
        //1 = right, 2 = left, 3 = up, 4 = down
        switch (movementDirection) {
            case 1 -> xPosition++;
            case 2 -> xPosition--;
            case 3 -> yPosition++;
            case 4 -> yPosition--;
            default -> {
            }
        }

        setCurrentPosition(xPosition, yPosition);
    }

    public boolean isNotNextToOther(int[] othersPosition) {
        int[] thisPosition = getCurrentPosition();
        return (thisPosition[0] != othersPosition[0] + 1 && thisPosition[0] != othersPosition[0] - 1) && (thisPosition[1] != othersPosition[1] + 1 && thisPosition[1] != othersPosition[1] - 1);
    }

    @Override
    public String toString() {
        return name + "'s current position is " + currentPosition[0] + ", " + currentPosition[1] + ".";
    }
}