import java.util.Random;

public class NPC {

    private String name;
    private boolean isInteractive; //Går att interagera med NPC eller är en del av världen
    private boolean isEnemy;
    private int xPosition;
    private int yPosition;
    private int[] currentPosition = new int[2];

    public NPC(String name, boolean isInteractive, boolean isEnemy, int xPosition, int yPosition) {
        this.name = name;
        this.isInteractive = isInteractive;
        this.isEnemy = isEnemy;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        setCurrentPosition(xPosition, yPosition);
    }

    private void setCurrentPosition(int xPosition, int yPosition) {
        currentPosition[0] = xPosition;
        currentPosition[1] = yPosition;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public int getxPosition() {
        return xPosition;
    }
    public int getyPosition() {
        return yPosition;
    }

    //Public for testing purpose
    public int generateRandomMovementNumber() {
        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }

    //When an NPC is idle, meaning not interacting with the Player in any way, the NPC has the ability to
    // walk around randomly on the map.
    public void idleMove() {
        int movementDirection = generateRandomMovementNumber();
        //1 = right, 2 = left, 3 = up, 4 = down, 5 = no movement
        switch (movementDirection) {
            case 1 -> xPosition++;
            case 2 -> xPosition--;
            case 3 -> yPosition++;
            case 4 -> yPosition--;
            case 5 -> {}
            default -> {
            }
        }
        setCurrentPosition(xPosition, yPosition);
    }


    @Override
    public String toString() {
        return name + "'s current position is " + currentPosition[0] + ", " + currentPosition[1] + ".";
    }

    public static void main(String[] args) {
        NPC randy = new NPC("Randy", true, false, 50 , 50);
        System.out.println(randy);
        randy.idleMove();
        System.out.println(randy);
        randy.idleMove();
        System.out.println(randy);
        randy.idleMove();
        System.out.println(randy);
        randy.idleMove();
        System.out.println(randy);
        randy.idleMove();
        System.out.println(randy);
        randy.idleMove();
        System.out.println(randy);
        randy.idleMove();
        System.out.println(randy);
        System.out.println(randy.generateRandomMovementNumber());
        System.out.println(randy.generateRandomMovementNumber());
        System.out.println(randy.generateRandomMovementNumber());
        System.out.println(randy.generateRandomMovementNumber());
        System.out.println(randy.generateRandomMovementNumber());
        System.out.println(randy.generateRandomMovementNumber());
    }

}