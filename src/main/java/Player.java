import java.util.HashMap;

public class Player implements Movement {
    private static final int STEPS = 5; //Kan skapa intressanta testfall

    private Player.Experience xp;

    private HashMap<Attributes, Integer> attributes;
    private FacingDirection playerFacingDirection;

    Map map;

    Player() {
        generateAttributeList();
        this.xp = new Experience();
        setPlayerFacingDirection(FacingDirection.UP);
    }

    public void setMap(Map m){
        map = m;
    }

    public void addDexterity(int amount) {
        attributes.merge(Attributes.DEXTERITY, amount, Integer::sum);
    }

    static class Experience {
        private int lvl;
        private int currentXp;
        private int cap;

        Experience() {
            this.lvl = 1;
            this.currentXp = 0;
            this.cap = lvl * 100;
        }

        int getRemainingXp() {
            return cap - currentXp;
        }

        void updateXp(int amount) {
            currentXp += amount;

            if (currentXp >= cap) {
                int rest = currentXp - cap;
                lvl += 1;
                currentXp = rest;
                cap = lvl * 100;
            }
        }
    }

    public HashMap<Attributes, Integer> getAttributes() {
        return attributes;
    }

    private void generateAttributeList() {
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

    public void addXp(int amount) {
        xp.updateXp(amount);
    }

    @Override
    public void moveUp() {
        map.updatePlayerPosition(Direction.UP);
        setPlayerFacingDirection(FacingDirection.UP);
    }

    @Override
    public void moveDown() {
        map.updatePlayerPosition(Direction.DOWN);
        setPlayerFacingDirection(FacingDirection.DOWN);
    }

    @Override
    public void moveRight() {
        setPlayerFacingDirection(FacingDirection.RIGHT);
        map.updatePlayerPosition(Direction.RIGHT);
    }

    @Override
    public void moveLeft() {
        map.updatePlayerPosition(Direction.LEFT);
        setPlayerFacingDirection(FacingDirection.LEFT);
    }

    private void setPlayerFacingDirection(FacingDirection direction) {
        playerFacingDirection = direction;
    }

    public FacingDirection getPlayerFacingDirection() {
        return playerFacingDirection;
    }

}
