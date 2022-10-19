public class FriendlyNPC extends NPC {

    private final boolean isQuestGiver;

    // public FriendlyNPC(String name, int xPosition, int yPosition) {
    public FriendlyNPC(String name) {
        super(name);
        isQuestGiver = false;
    }


    public FriendlyNPC(String name, boolean isQuestGiver) {
        // super(name, xPosition, yPosition);
        super(name);
        this.isQuestGiver = isQuestGiver;
    }

    public Quest giveQuest(int questID) {
        Quest q = new Quest();
        q = q.getQuestByID(questID);

        System.out.println();
        return q;
    }

    public String interactWithPlayer() {
        String say = "Hello adventurer.";
        if (!isQuestGiver) {
            System.out.println(say);
            return say;
        } else {

            //Interact with player, give quests osv
        }
        return say;
    }


}
