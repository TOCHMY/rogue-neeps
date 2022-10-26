package quest;

import npc.EnemyNPC;
import npc.FriendlyNPC;

public class Quest {

    private final int questID;
    private final String questName;
    private boolean isInitiated;
    private boolean isCompleted;
    private boolean isReturnedToQuestGiver; // == means the quest is finished
    private String questDescription;
    private String questGoalText;
    private int killQuestCurrentKilled;
    private int amountOfEnemiesToKill;
    private EnemyNPC killQuestTarget;
    private FriendlyNPC talkQuestTarget;
    private FriendlyNPC questGiver;

    public Quest(int questID, String questName) {
        this.questID = questID;
        this.questName = questName;
    }

    public void setQuestGiver(FriendlyNPC npc) {
        this.questGiver = npc;
    }

    public FriendlyNPC getQuestGiver() {
        return questGiver;
    }

    public void setKillQuestTarget(EnemyNPC npc) {
        this.killQuestTarget = npc;
    }
    public EnemyNPC getKillQuestTarget() {
        return killQuestTarget;
    }

    public void setTalkQuestTarget(FriendlyNPC npc) {
        this.talkQuestTarget = npc;
    }

    public FriendlyNPC getTalkQuestTarget() {
        return talkQuestTarget;
    }

    public String getQuestName() {
        return questName;
    }

    public int getQuestID() {
        return questID;
    }

    public int getKillQuestCurrentKilled() {
        return killQuestCurrentKilled;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestGoalText(String questGoalText) {
        this.questGoalText = questGoalText;
    }

    public String getQuestGoalText() {
        return questGoalText;
    }

    public void setAmountOfEnemiesToKill(int amountOfEnemiesToKill) {
        this.amountOfEnemiesToKill = amountOfEnemiesToKill;
    }

    public int getAmountOfEnemiesToKill() {
        return amountOfEnemiesToKill;
    }

    public boolean isReturnedToQuestGiver() {
        return isReturnedToQuestGiver;
    }

    public void setReturnedToQuestGiver(boolean returnedToQuestGiver) {
        isReturnedToQuestGiver = returnedToQuestGiver;
    }

    public void incrementEnemiesKilled() {
        killQuestCurrentKilled++;
    }

    public void updateKillQuestStatus(EnemyNPC npc) {
        if (npc.getName().equals(killQuestTarget.getName())) {
            if (killQuestCurrentKilled != amountOfEnemiesToKill) {
                incrementEnemiesKilled();
                if (killQuestCurrentKilled == amountOfEnemiesToKill) {
                    printQuestCompleted();
                    setCompleted(true);
                }
            }
        }
    }

    public void printQuestCompleted() {
        System.out.println("Quest " + getQuestName() + " is completed. Return to " + getQuestGiver().getName() + ".");
    }

    public void resetKillQuest() {
        isInitiated = false;
        isCompleted = false;
        killQuestCurrentKilled = 0;
    }

    public void updateTalkQuestStatus(FriendlyNPC npc) {
        if (npc.equals(talkQuestTarget)) {
            setCompleted(true);
        }
    }

    public String printKillQuestStatus() {
        return killQuestCurrentKilled + " of " + amountOfEnemiesToKill + " " + killQuestTarget.getName().toLowerCase()  + "s killed.";
    }
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isInitiated() {
        return isInitiated;
    }

    public void setInitiated(boolean initiated) {
        isInitiated = initiated;
    }


    @Override
    public String toString() {
        return "Quest name: " + questName + ". QuestID: " + questID;
    }
}
