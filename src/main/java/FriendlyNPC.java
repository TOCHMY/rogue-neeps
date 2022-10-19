import java.io.InputStream;
import java.util.ArrayList;

public class FriendlyNPC extends NPC implements Movement {

    private String name;
    private final boolean isQuestGiver;
    private Quest questToGive;

    private QuestDatabase qdb = new QuestDatabase();

    public FriendlyNPC(String name) {
        this.name = name;
        isQuestGiver = false;
    }


    public FriendlyNPC(String name, boolean isQuestGiver) {
        this.name = name;
        this.isQuestGiver = isQuestGiver;
    }

    //Give NPC a quest by questID
    public void getQuestFromDatabase(int questID) {
        Quest q = qdb.getQuest(questID);
        assignQuestToNPC(q);
    }

    public void assignQuestToNPC(Quest quest) {
        this.questToGive = quest;
    }

    public Quest giveQuest() {
        return questToGive;
    }

    public String nonQuestGiverResponse() {
        return "Hello adventurer.";
    }

    public String respondWithQuest() {
        return questToGive.getQuestDescription();
    }

    private void handleQuestAccept() {
        questToGive.setInitiated(true);
        System.out.println(questToGive.isInitiated());
    }

    public String askToAcceptQuest(UserInputAsker uia) throws IllegalArgumentException {
        String response = uia.ask("Do you accept this quest? y / n");
        if (response.equals("y")) {
            handleQuestAccept();
            return "Thank you for helping me!";
        } else if (response.equals("n")) {
            return "Oh, okay...";
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public FriendlyNPC getFriendlyNPC() {
        return this;
    }

    public String getName() {
        return this.name;
    }
}
