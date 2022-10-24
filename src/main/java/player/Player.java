package player;


import map.Map;
import nicoMap.NicoMap;
import npc.EnemyNPC;
import npc.FriendlyNPC;
import quest.Quest;
import util.Direction;
import util.Movement;
import util.Position;
import util.UserInputAsker;
import item.ItemCollection;
import item.Shield;
import item.Weapon;

import java.util.ArrayList;
import java.util.List;


public abstract class Player implements Movement {
    private final ArrayList<Quest> questLog = new ArrayList<>();
    private final ArrayList<Quest> finishedQuestsLog = new ArrayList<>();

    private Player.Experience xp;
    Stats stats;
    private Direction playerFacingDirection;
    public Map map;
    ItemCollection items;
    NicoMap nm;
    int hp;
    Position position;

    Player(int strength, int dexterity, int intelligence, int hp) {
        this.hp = hp;
        stats = new Stats(strength, dexterity, intelligence);
        xp = new Experience();
        setPlayerFacingDirection(Direction.UP);
    }

    abstract void equip(Weapon weapon);
    abstract void equip(Shield shield);

    abstract List<Weapon> canEquip();

    public Stats getStats(){
        return stats;
    }

    public void setMap(Map m){
        map = m;
    }

    public void bindMap(NicoMap nicoMap) {
        nm = nicoMap;
    }

    public void setPosition(Position pos) {
        position = pos;
    }

    public Position getPosition() {
        return position;
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
            throw new IllegalStateException("Quest is not accepted.");
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

    //Handles quests with the goal of talking to another NPC
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
    public void moveUp(){

        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }

        map.updatePlayerPosition(Direction.UP, this);
        setPlayerFacingDirection(Direction.UP);
    }

    @Override
    public void moveDown() {

        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        map.updatePlayerPosition(Direction.DOWN, this);
        setPlayerFacingDirection(Direction.DOWN);
    }

    @Override
    public void moveRight() {

        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        map.updatePlayerPosition(Direction.RIGHT, this);
        setPlayerFacingDirection(Direction.RIGHT);

        // nm.move(util.Direction.RIGHT);
    }

    @Override
    public void moveLeft() {

        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
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