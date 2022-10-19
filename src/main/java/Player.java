import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Movement {
    private static final int STEPS = 5; //Kan skapa intressanta testfall
    private static final int FACING_UP = 1;
    private static final int FACING_RIGHT = 2;
    private static final int FACING_DOWN = 3;
    private static final int FACING_LEFT = 4;

    private final ArrayList<Quest> questLog = new ArrayList<>();

    private Player.Experience xp;

    private HashMap<Attributes, Integer> attributes;
    private int playerFacingDirection;

    Map map;

    Player() {
        generateAttributeList();
        this.xp = new Experience();
        setPlayerFacingDirection(FACING_UP);
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

    public void addQuestToQuestLog(Quest quest) {
        if (quest.isInitiated) {
            questLog.add(quest);
        } else {
            throw new IllegalStateException("Quest is not accepted");
        }

    }

    public Quest getQuestFromQuestLog(int questID) {
    return questLog.get(questID);
    }

    @Override
    public void moveUp() {
        map.updatePlayerPosition(Direction.UP, this);
        setPlayerFacingDirection(FACING_UP);
    }

    @Override
    public void moveDown() {
        map.updatePlayerPosition(Direction.DOWN, this);
        setPlayerFacingDirection(FACING_DOWN);
    }

    @Override
    public void moveRight() {
        map.updatePlayerPosition(Direction.RIGHT, this);
        setPlayerFacingDirection(FACING_RIGHT);
    }

    @Override
    public void moveLeft() {
        map.updatePlayerPosition(Direction.LEFT, this);
        setPlayerFacingDirection(FACING_LEFT);
    }

    private void setPlayerFacingDirection(int direction) {
        playerFacingDirection = direction;
    }

    public int getPlayerFacingDirection() {
        return playerFacingDirection;
    }

}