import java.util.Random;

public class NPC {

    //Set NPC movement range? <- så typ en specifik NPC kan inte röra sig längre än 2 pixlar
    // från sin initialPosition.
    private final String name;

    //tanken är att initialPosition ska användas för att se till att NPC inte vandrar för långt bort på kartan
    private int[] initialPosition = new int[2];


    public NPC(String name) {
        this.name = name;
    }




    private int generateRandomMovementNumber() {
        Random rand = new Random();
        return rand.nextInt(4) + 1;
    }

    private int generateRandomWaitNumber() {
        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }


//    public boolean isNotNextToOther(int[] othersPosition) {
//        int[] thisPosition = getCurrentPosition();
//        return (thisPosition[0] != othersPosition[0] + 1 && thisPosition[0] != othersPosition[0] - 1) && (thisPosition[1] != othersPosition[1] + 1 && thisPosition[1] != othersPosition[1] - 1);
//    }

    @Override
    public String toString() {

        return name + ".";
    }
}