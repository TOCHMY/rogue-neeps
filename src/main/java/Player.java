import java.util.HashMap;

public class Player implements Movement, Positionable{
    private static int STEPS = 5;

    private Player.Experience xp;

    private HashMap<Attributes, Integer> attributes;

    private int currentX;
    private int currentY;

    Player(){
        this.currentX = 0;
        this.currentY = 0;
        generateAttributeList();
        this.xp = new Experience();
    }

    public void addDexterity(int amount){
        attributes.merge(Attributes.DEXTERITY, amount, Integer::sum);
    }

    static class Experience{
        private int lvl;
        private int currentXp;
        private int cap;

        Experience(){
            this.lvl = 1;
            this.currentXp = 0;
            this.cap = lvl * 100;
        }
       int getRemainingXp(){
            return cap - currentXp;
        }
        void updateXp(int amount){
            currentXp += amount;

            if(currentXp >= cap){
                int rest = currentXp - cap;
                lvl += 1;
                currentXp = rest;
                cap = lvl * 100;
            }
        }
    }

    public HashMap<Attributes, Integer> getAttributes(){
        return attributes;
    }

    private void generateAttributeList(){
        attributes = new HashMap<>();
        attributes.put(Attributes.STRENGTH, 1);
        attributes.put(Attributes.DEXTERITY, 1);
        attributes.put(Attributes.INTELLIGENCE, 1);
    }
    public int getLvl() {
       return xp.lvl;
    }
    public int getRemainingXp() {
        return xp.getRemainingXp();
    }
    public void addXp(int amount){
        xp.updateXp(amount);
    }

    @Override
    public void moveUp() {
        currentY += (int) (STEPS + (attributes.get(Attributes.DEXTERITY)/10));
    }

    @Override
    public void moveDown() {
        currentY -= (int) (STEPS + (attributes.get(Attributes.DEXTERITY)/10));
    }

    @Override
    public void moveRight() {
        currentX += (int) (STEPS + (attributes.get(Attributes.DEXTERITY)/10));
    }

    @Override
    public void moveLeft() {
        currentX -= (int) (STEPS + (attributes.get(Attributes.DEXTERITY)/10));
    }

    @Override
    public int getCurrentX() {
        return currentX;
    }

    @Override
    public int getCurrentY() {
        return currentY;
    }
}
