package player;


import map.Map;
import map.Tile;
import npc.EnemyNPC;
import npc.FriendlyNPC;
import quest.Quest;
import util.*;
import item.ItemCollection;
import item.Shield;
import item.Weapon;

import java.util.ArrayList;
import java.util.List;


public abstract class Player {
    private final ArrayList<Quest> questLog = new ArrayList<>();
    private final ArrayList<Quest> finishedQuestsLog = new ArrayList<>();

    private Player.Experience xp;
    Stats stats;
    private Direction playerFacingDirection;
    public Map map;
    ItemCollection items;
    int hp;
    Position position;

    Player(int strength, int dexterity, int intelligence, int hp) {
        this.hp = hp;
        stats = new Stats(strength, dexterity, intelligence);
        xp = new Experience();
        setPlayerFacingDirection(Direction.UP);
    }

    private double attackDamage(){
      int playerStrength = stats.getStrength();
      double attackFromItem = items.attackWithItems();
      double dmg = playerStrength + (playerStrength * (.1 * attackFromItem));
      return dmg;
    }
    public void attack(Killable target){
        target.takeDmg(this, attackDamage());
    }
    public abstract boolean canMove(Tile tile);
    abstract void equip(Weapon weapon);
    abstract void equip(Shield shield);

    abstract List<Weapon> canEquip();

    public Stats getStats(){
        return stats;
    }

    public void setMap(Map m){
        map = m;
        m.setPlayer(this);
        moveTo(map.STARTING_POS);
    }

    public void setPosition(Position pos) {
        position = pos;
    }

    public Position getPosition() {
        return position;
    }


    public void moveTo(Position pos){
        position = pos;
    }
    public void move(Direction dir) {
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        Tile tile = map.getTile(dir);
        if(!tile.isOccupied()){
            move(tile);
        }
        setPlayerFacingDirection(dir);
    }

    private void move(Tile target){
        if(map == null){
            throw new IllegalStateException("Cannot move without a map");
        }
        if(canMove(target)){
            position = target.getPosition();
        }

    }

    private void setPlayerFacingDirection(Direction direction) {
        playerFacingDirection = direction;
    }

    public Direction getPlayerFacingDirection() {
        return playerFacingDirection;
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
        }
    }

    public void abandonQuest(Quest quest) {
        if (questLog.contains(quest)) {
//            quest.setQuestGiver(quest.getQuestGiver());
            removeQuestFromQuestLog(quest);
        } else {
            throw new NullPointerException("Quest does not exist in quest log");
        }
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
    public int getLvl() {
        return xp.lvl;
    }

    public int getRemainingXp() {
        return xp.getRemainingXp();
    }

    public void addXp(int amount) {
        xp.updateXp(amount);
    }


}