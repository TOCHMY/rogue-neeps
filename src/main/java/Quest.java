import java.util.ArrayList;
import java.util.HashMap;

public class Quest {

    int questID;
    boolean isInitiated;
    boolean isCompleted;
    String questDescription;
    String questName;
    String questGoal;
    int killQuestCurrentKilled;
    ArrayList<Quest> questArrayList = new ArrayList<>();
    HashMap<Integer, Quest> questHashMap = new HashMap<>();

    Quest() {
        addQuests();
        putQuestsInMap();
    }

    Quest(int questID, String questName) {
        this.questID = questID;
        this.questName = questName;
    }

    public void acceptQuest(String input) {
        if (input.equals("y")) {
            isInitiated = true;
            isCompleted = false;
        }
    }

    public void completeQuest() {
        if (this.isCompleted) {
            //NPC option to complete
        }
    }

    public Quest getQuestByID(int questID) {
        return questArrayList.get(questID);
    }

    public Quest getQuestByIDFromHashMap(int questID) {
        return questHashMap.get(questID);
    }


    public String getQuestName(int questID) {
        return questArrayList.get(questID).questName;
    }

    public String getQuestHashName(int questID) {
        return questHashMap.get(questID).questName;
    }

    public String getQuestDescription(int questID) {
        return questArrayList.get(questID).questDescription;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }

    public void setQuestGoal(String questGoal) {
        this.questGoal = questGoal;
    }

    public String getQuestGoal(int questID) {
        return questArrayList.get(questID).questGoal;
    }


    public void setPigsKilled() {
        killQuestCurrentKilled++;
    }

    private int getPigsKilled() {
        return killQuestCurrentKilled;
    }

    public void pigMenaceQuestGoalHandler() {
        int pigsKilled = getPigsKilled();
        int totalPigsNeededToKill = 5;
        if (pigsKilled == totalPigsNeededToKill) {
            isCompleted = true;
            System.out.println(this.questName + " is completed!");
        }
    }

    private void pigMenace() {
        Quest q = new Quest(1, "Pig Menace");
        q.setQuestDescription("The pigs in this area has developed an attitude. Show them who's boss!");
        q.setQuestGoal("Kill 5 pigs.");
        q.isInitiated = false;
        questArrayList.add(q);
        q.pigMenaceQuestGoalHandler();
        questHashMap.put(questID, q);
    }

    private Quest pigMenaceHash() {
        Quest q = new Quest(1, "Pig Menace");
        q.setQuestDescription("The pigs in this area has developed an attitude. Show them who's boss!");
        q.setQuestGoal("Kill 5 pigs.");
        q.isInitiated = false;
        q.pigMenaceQuestGoalHandler();
        return q;
    }

    private void findHerbert() {
        Quest q = new Quest(2, "Find Herbert");
        q.setQuestDescription("My grandpa, Herbert, went picking flowers and is probably lost somewhere. " +
                "\nCan you find him for me and tell him to come home?");
        q.setQuestGoal("Find Herbert");
        q.isInitiated = false;
        questArrayList.add(q);
    }

    void addQuests() {
        addQuestFlagToArrayList();
        pigMenace();
        findHerbert();
    }

    //Existerar endast för att questID ska börja på 1 och höra ihop med ArrayList index.
    private void addQuestFlagToArrayList() {
        Quest q = new Quest(0, "Flag");
        questArrayList.add(q);
    }

    void putQuestsInMap() {
        questHashMap.put(pigMenaceHash().questID, pigMenaceHash());
    }

}
