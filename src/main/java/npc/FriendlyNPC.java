package npc;

import map.Tile;
import player.Player;
import quest.Quest;
import quest.QuestDatabase;
import util.UserInputAsker;

public class FriendlyNPC extends NPC {
    private final boolean isQuestGiver;
    private String dialog = "";
    private Quest questToGive;
    private boolean isQuestGoal = false;

    public FriendlyNPC(String name) {
        super(name, 50);
        isQuestGiver = false;
    }


    public FriendlyNPC(String name, boolean isQuestGiver) {
        super(name, 50);
        this.isQuestGiver = isQuestGiver;
    }

    //Give npc.NPC a quest by questID
    public void getQuestFromDatabase(int questID, QuestDatabase qdb) {
        Quest q = qdb.getQuest(questID);
        setDialog(q.getQuestDescription());
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

    //Return value could be used in some sort of chat-UI
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
            System.out.println("Quest \"" + quest.getQuestName() + "\" completed.");
            quest.setReturnedToQuestGiver(true);
            questToGive = null;
            player.addFinishedQuestToFinishedQuestLog(quest);
            player.removeQuestFromQuestLog(quest);
        } else {
            //Code to indicate quest is not completed
            System.out.println("Quest goal " + questToGive.getQuestGoalText() + " not completed yet.");
        }
    }
    @Override
    public boolean canMove(Tile tile) {
        return tile.isRoomTile();
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
