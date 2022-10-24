package npc;

import player.Player;
import quest.Quest;
import quest.QuestDatabase;
import util.Movement;
import util.UserInputAsker;

public class FriendlyNPC extends NPC implements Movement {

    private final String name;
    private String dialog = "";
    private final boolean isQuestGiver;
    private Quest questToGive;

    private boolean isQuestGoal = false;

    public FriendlyNPC(String name) {
        this.name = name;
        isQuestGiver = false;
    }


    public FriendlyNPC(String name, boolean isQuestGiver) {
        this.name = name;
        this.isQuestGiver = isQuestGiver;
    }

    //Give npc.NPC a quest by questID
    public void getQuestFromDatabase(int questID, QuestDatabase qdb) {
        Quest q = qdb.getQuest(questID);
        assignQuestToNPC(q);
    }

    public void assignQuestToNPC(Quest quest) {
        this.questToGive = quest;
        quest.setQuestGiver(this);
    }

    public void setQuestGoal() {
        isQuestGoal = true;
    }

    public boolean isQuestGoal() {
        return isQuestGoal;
    }

    public boolean isQuestGiver() {
        return isQuestGiver;
    }

    public Quest getAssignedQuest() {
        return questToGive;
    }

    public void offerQuest() {
        System.out.println(questToGive.getQuestDescription());
    }

    public String nonQuestGiverResponse() {
        return "Hello adventurer.";
    }

    public String respondWithQuest() {
        return questToGive.getQuestDescription();
    }

    private void handleQuestAccepted(Player player) {
        questToGive.setInitiated(true);
        player.addQuestToQuestLog(questToGive);
    }

    public String askToAcceptQuest(UserInputAsker uia, Player player) throws IllegalArgumentException {
        offerQuest();
        String response = uia.ask("Do you accept this quest? y / n");
        if (response.equals("y")) {
            handleQuestAccepted(player);
            return "Thank you for helping me!";
        } else if (response.equals("n")) {
            return "Oh, okay...";
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public void completeQuest(Quest quest, Player player) {
        if (quest.equals(questToGive) && questToGive.isCompleted()) {
            System.out.println("quest.Quest \"" + quest.getQuestName() + "\" completed.");
            quest.setReturnedToQuestGiver(true);
            player.addFinishedQuestToFinishedQuestLog(quest);
            player.removeQuestFromQuestLog(quest);
        } else {
            System.out.println("quest.Quest goal " + questToGive.getQuestGoalText() + " not completed yet.");
        }
    }

    public void setDialog(String text) {
        this.dialog = text;
    }

    public String say() {
        return dialog;
    }

    public String getName() {
        return this.name;
    }
}
