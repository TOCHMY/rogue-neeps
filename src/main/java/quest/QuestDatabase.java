package quest;

import npc.Albatross;
import npc.EnemyNPC;
import npc.FriendlyNPC;
import npc.Pig;

import java.util.ArrayList;
public class QuestDatabase {

    private final ArrayList<Quest> questList = new ArrayList<>();

    //Flag existerar endast för att fylla plats 0 i arrayen i syfte att kunna hämta quest från questList genom questID
    // där questID alltid == questList index.
    private static final Quest FLAG = new Quest(0, "");
    private final Quest pigMenace = new Quest(1, "Pig Menace");
    private final Quest findHerbert = new Quest(2, "Find Herbert");
    private final Quest hungryAlbatross = new Quest(3, "Hungry Albatross");
    private final Quest killTwoPigs = new Quest(4, "Kill Two Pigs");
    public QuestDatabase() {
        setup();
    }

    private void setup() {
        initiatePigMenace();
        initiateFindHerbert();
        initiateHungryAlbatross();
        initiateKillTwoPigs();
        addDefinedQuestsToDatabase();
    }

    private void initiatePigMenace() {
        final EnemyNPC pig = new Pig();
        pigMenace.setQuestDescription("The pigs in this area has developed an attitude. Show them who's boss!");
        pigMenace.setQuestGoalText("kill 5 pigs");
        pigMenace.setAmountOfEnemiesToKill(5);
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
        npcHerbert.setQuestGoal();
        findHerbert.setInitiated(false);
        findHerbert.setCompleted(false);
    }

    private void initiateHungryAlbatross() {
        final EnemyNPC albatross = new Albatross();
        hungryAlbatross.setQuestDescription("There's a hungry albatross in the area that eats all my veggies. Kill it!");
        hungryAlbatross.setQuestGoalText("kill the hungry albatross");
        hungryAlbatross.setAmountOfEnemiesToKill(1);
        hungryAlbatross.setKillQuestTarget(albatross);
        hungryAlbatross.setInitiated(false);
        hungryAlbatross.setCompleted(false);
    }

    private void initiateKillTwoPigs() {
        final EnemyNPC pig = new Pig();
        killTwoPigs.setQuestDescription("Please go out into the field and slaughter two pigs for me.");
        killTwoPigs.setQuestGoalText("kill 2 pigs");
        killTwoPigs.setAmountOfEnemiesToKill(2);
        killTwoPigs.setKillQuestTarget(pig);
        killTwoPigs.setInitiated(false);
        killTwoPigs.setCompleted(false);
    }

    private void addDefinedQuestsToDatabase() {
        questList.add(FLAG);
        questList.add(pigMenace);
        questList.add(findHerbert);
        questList.add(hungryAlbatross);
        questList.add(killTwoPigs);
    }

    public Quest getQuest(int questID) {
        return questList.get(questID);
    }
}
