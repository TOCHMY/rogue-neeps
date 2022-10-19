import java.util.ArrayList;
import java.util.HashMap;

public class Quest {

    private int questID;
    private boolean isInitiated;
    private boolean isCompleted;
    private String questDescription;
    private String questName;
    private String questGoal;
    private int killQuestCurrentKilled;

    public Quest() {

    };

    public Quest(int questID, String questName) {
        this.questID = questID;
        this.questName = questName;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestGoal(String questGoal) {
        this.questGoal = questGoal;
    }
    public void setEnemiesKilled() {
        killQuestCurrentKilled++;
    }

    private int getEnemiesKilled() {
        return killQuestCurrentKilled;
    }

    public void pigMenaceQuestGoalHandler() {
        int pigsKilled = getEnemiesKilled();
        int totalPigsNeededToKill = 5;
        if (pigsKilled == totalPigsNeededToKill) {
            isCompleted = true;
            System.out.println(this.questName + " is completed!");
        }
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
