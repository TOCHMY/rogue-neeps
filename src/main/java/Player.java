import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

abstract class Player implements Movement {
    private static final int STEPS = 5; //Kan skapa intressanta testfall
    private final ArrayList<Quest> questLog = new ArrayList<>();
    private final ArrayList<Quest> finishedQuestsLog = new ArrayList<>();

    private Player.Experience xp;

    private int strength;
    private int dexterity;
    private int intelligence;
    int hp;
    private Direction playerFacingDirection;

    Map map;

    Player(int strength, int dexterity, int intelligence, int hp) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.hp = hp;
        this.xp = new Experience();
        setPlayerFacingDirection(Direction.UP);
    }

    abstract void wield(Weapon w);

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setMap(Map m) {
        map = m;
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

    //temporary method to kill enemey npc
    public void killTarget(EnemyNPC target) {
        target.die();

        for (Quest q : questLog) {
            if (q.getKillQuestTarget() == null) { // if quest isn't a killquest
                continue;
            }
            if (q.getKillQuestTarget().getName().equals(target.getName())) {
                q.updateKillQuestStatus(target);
            }
        }
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

    public ArrayList<Quest> getQuestLog() {
        return questLog;
    }

    public void addQuestToQuestLog(Quest quest) {
        if (quest.isInitiated()) {
            questLog.add(quest);
        } else {
            throw new IllegalStateException("Quest is not accepted");
        }
    }

    public void removeQuestFromQuestLog(Quest quest) {
        questLog.remove(quest);
    }

    public void addFinishedQuestToFinishedQuestLog(Quest quest) {
        finishedQuestsLog.add(quest);
    }

    public ArrayList<Quest> getFinishedQuestsLog() {
        return new ArrayList<>(finishedQuestsLog);
    }

    public Quest getQuestFromQuestLog(Quest quest) {
        for (Quest q : questLog) {
            if (q.equals(quest)) {
                return q;
            }
        }
        return null;
    }

    public void interactWithFriendlyNPC(UserInputAsker uia, FriendlyNPC target) {
        String response = uia.ask("Talk to " + target.getName() + "? y / n");
        if (response.equals("y")) {
            target.say();
            if (target.isQuestGiver()) {
                handleQuestGiverInteraction(target);
            }
            if (target.isQuestGoal()) {
                talkQuestHandler(target);
            }
        }
    }

    private void talkQuestHandler(FriendlyNPC npc) {
        for (Quest q : questLog) {
            if (q.getTalkQuestTarget().equals(npc)) {
                q.updateTalkQuestStatus(npc);
                q.printQuestCompleted();
            }
        }
    }

    private void handleQuestGiverInteraction(FriendlyNPC npc) {
        if (questLog.contains(npc.getAssignedQuest())) {
            for (Quest q : questLog) {
                if (q.isCompleted() && q.getQuestGiver().equals(npc)) {
                    q.setReturnedToQuestGiver(true);
                    npc.completeQuest(q, this);
                    break;
                }
            }
        }  else {
            npc.offerQuest();
        }
    }

    public void abandonQuest(Quest quest) {
        if (questLog.contains(quest)) {
            removeQuestFromQuestLog(quest);
        } else {
            throw new NullPointerException("Quest does not exist in quest log");
        }
    }

    @Override
    public void moveUp() {
        map.updatePlayerPosition(Direction.UP, this);
        setPlayerFacingDirection(Direction.UP);
    }

    @Override
    public void moveDown() {
        map.updatePlayerPosition(Direction.DOWN, this);
        setPlayerFacingDirection(Direction.DOWN);
    }

    @Override
    public void moveRight() {
        map.updatePlayerPosition(Direction.RIGHT, this);
        setPlayerFacingDirection(Direction.RIGHT);
    }

    @Override
    public void moveLeft() {
        map.updatePlayerPosition(Direction.LEFT, this);
        setPlayerFacingDirection(Direction.LEFT);
    }

    private void setPlayerFacingDirection(Direction direction) {
        playerFacingDirection = direction;
    }

    public Direction getPlayerFacingDirection() {
        return playerFacingDirection;
    }

}