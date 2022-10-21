public class Stats {
    private int strength;
    private int dexterity;
    private int intelligence;



    Stats(int strength, int dexterity, int intelligence){
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;


    }

    public int getStrength() {
        return strength;
    }

    public void addStrength(int amount) {
        this.strength += amount;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void addDexterity(int amount) {
        this.dexterity += amount;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void addIntelligence(int amount) {
        this.intelligence += amount;
    }



}
