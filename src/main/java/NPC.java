import java.util.Random;

public class NPC {

    //Set NPC movement range? <- så typ en specifik NPC kan inte röra sig längre än 2 pixlar
    // från sin initialPosition.
    private final String name;
    private int xPosition;
    private int yPosition;
    private int[] currentPosition = new int[2];
    private final int[] initialPosition = new int[2];

    public NPC(String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        initialPosition[0] = xPosition;
        initialPosition[1] = yPosition;
        setCurrentPosition(xPosition, yPosition);
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

    @Override
    public String toString() {
        return name + "'s current position is " + currentPosition[0] + ", " + currentPosition[1] + ".";
    }

    public static void main(String[] args) {
        NPC randy = new NPC("Randy", 50 , 50);
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