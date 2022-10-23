package quest;

import npc.EnemyNPC;
import npc.FriendlyNPC;
import npc.Pig;

import java.util.ArrayList;
public class QuestDatabase {

    ArrayList<Quest> questList = new ArrayList<>();

    //Flag existerar endast för att fylla plats 0 i arrayen i syfte att kunna hämta quest från questList genom questID
    // där questID alltid == questList index.
    private static final Quest FLAG = new Quest(0, "");
    private final Quest pigMenace = new Quest(1, "npc.Pig Menace");
    private final Quest findHerbert = new Quest(2, "Find Herbert");
    public QuestDatabase() {
        setup();
    }

    private void setup() {
        initiatePigMenace();
        initiateFindHerbert();
        addQuestsToDatabase();
    }

    private void initiatePigMenace() {
        final EnemyNPC pig = new Pig();
        pigMenace.setQuestDescription("The pigs in this area has developed an attitude. Show them who's boss!");
        pigMenace.setQuestGoalText("kill 5 pigs");
        pigMenace.setKillQuestGoal(5);
        pigMenace.setKillQuestTarget(pig);
        pigMenace.setInitiated(false);
        pigMenace.setCompleted(false);
    }

    private void initiateFindHerbert() {
        FriendlyNPC npcHerbert = new FriendlyNPC("Herbert");
        findHerbert.setQuestDescription("My grandpa, Herbert, went picking flowers and is probably lost somewhere. " +
                "\nCan you find him for me and tell him to come home?");
        findHerbert.setQuestGoalText("find Herbert");
        findHerbert.setTalkQuestTarget(npcHerbert);
        findHerbert.setInitiated(false);
        findHerbert.setCompleted(false);
    }

    private void addQuestsToDatabase() {
        questList.add(FLAG);
        questList.add(pigMenace);
        questList.add(findHerbert);
    }

    public Quest getQuest(int questID) {
        return questList.get(questID);
    }
}
