import java.util.ArrayList;
public class QuestDatabase {

    ArrayList<Quest> questList = new ArrayList<>();

    //Flag existerar endast för att fylla plats 0 i arrayen
    private static final Quest FLAG = new Quest(0, "");
    private final Quest pigMenace = new Quest(1, "Pig Menace");
    private final Quest findHerbert = new Quest(2, "Find Herbert");
    QuestDatabase() {
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
        pigMenace.setQuestGoalText("Kill 5 pigs.");
        pigMenace.setKillQuestGoal(5);
        pigMenace.setKillQuestTarget(pig);
        pigMenace.setInitiated(false);
        pigMenace.setCompleted(false);
    }

    private void initiateFindHerbert() {
        findHerbert.setQuestDescription("My grandpa, Herbert, went picking flowers and is probably lost somewhere. " +
                "\nCan you find him for me and tell him to come home?");
        findHerbert.setQuestGoalText("Find Herbert");
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
