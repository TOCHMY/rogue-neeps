import java.util.ArrayList;

public class FriendlyNPC extends NPC {

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

    public void assignQuestToNPC(int questID) {
        questToGive = qdb.getQuest(questID);
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

    public FriendlyNPC getFriendlyNPC() {
        return this;
    }

    public String getName() {
        return this.name;
    }
}
