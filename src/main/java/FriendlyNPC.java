import java.io.InputStream;
import java.util.ArrayList;

public class FriendlyNPC extends NPC implements Movement {

    private final String name;
    private final boolean isQuestGiver;
    private Quest questToGive;

    public FriendlyNPC(String name) {
        this.name = name;
        isQuestGiver = false;
    }


    public FriendlyNPC(String name, boolean isQuestGiver) {
        this.name = name;
        this.isQuestGiver = isQuestGiver;
    }

    //Give NPC a quest by questID
    public void getQuestFromDatabase(int questID, QuestDatabase qdb) {
        Quest q = qdb.getQuest(questID);
        assignQuestToNPC(q);
    }

    public void assignQuestToNPC(Quest quest) {
        this.questToGive = quest;

    }

    public Quest getAssignedQuest() {
        return questToGive;
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

    private void handleQuestAccept(Player player) {
        questToGive.setInitiated(true);
        player.addQuestToQuestLog(questToGive);
    }

    public String askToAcceptQuest(UserInputAsker uia, Player player) throws IllegalArgumentException {
        String response = uia.ask("Do you accept this quest? y / n");
        if (response.equals("y")) {
            handleQuestAccept(player);
            return "Thank you for helping me!";
        } else if (response.equals("n")) {
            return "Oh, okay...";
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public void completeQuest(Quest quest, Player player) {
        if (quest.equals(questToGive) && questToGive.isCompleted()) {
            System.out.println("Thank you for your help!");
            player.removeQuestFromQuestLog(quest);
        }
    }


    public FriendlyNPC getFriendlyNPC() {
        return this;
    }

    public String getName() {
        return this.name;
    }
}
