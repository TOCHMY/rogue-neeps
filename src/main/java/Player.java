import java.util.HashMap;

public class Player {

    private int strength;
    private int dexterity;
    private int intelligence;

    private Player.Experience xp;

    private HashMap<String, Integer> attributes;

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
    Player(){
        this.strength = 0;
        this.dexterity = 0;
        this.intelligence = 0;
        generateAttributeList();
        this.xp = new Experience();
    }

    public HashMap<String, Integer> getAttributes(){
        return attributes;
    }

    private void generateAttributeList(){
        attributes = new HashMap<>();
        attributes.put("strength", 0);
        attributes.put("dexterity", 0);
        attributes.put("intelligence", 0);
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



}
