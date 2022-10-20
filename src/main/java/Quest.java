import java.util.ArrayList;
import java.util.HashMap;

public class Quest {

    private final int questID;
    private boolean isInitiated;
    private boolean isCompleted;
    private String questDescription;
    private final String questName;
    private String questGoalText;
    private int killQuestCurrentKilled;
    private int amountOfEnemiesToKill;
    private EnemyNPC killQuestTarget;


    public Quest(int questID, String questName) {
        this.questID = questID;
        this.questName = questName;
    }

    public void setQuestGiver(FriendlyNPC npc) {
        npc.assignQuestToNPC(this);
    }

    public void setKillQuestTarget(EnemyNPC npc) {
        this.killQuestTarget = npc;
    }

    public EnemyNPC getKillQuestTarget() {
        return killQuestTarget;
    }

    public String getQuestName() {
        return questName;
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

    public void setKillQuestGoal(int amountOfEnemiesToKill) {
        this.amountOfEnemiesToKill = amountOfEnemiesToKill;
    }

    public int getQuestID() {
        return questID;
    }

    public void incrementEnemiesKilled() {
        killQuestCurrentKilled++;
    }

    public void updateKillQuestStatus(EnemyNPC npc) {
        if (npc.getName().equals(killQuestTarget.getName())) {
            if (killQuestCurrentKilled != amountOfEnemiesToKill) {
                incrementEnemiesKilled();
                if (killQuestCurrentKilled == amountOfEnemiesToKill) {
                    setCompleted(true);
                }
            }
        }

    }


    //fixa snygg string
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
        return "Quest name: " + questName + " with questID " + questID;
    }


}
