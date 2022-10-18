public class FriendlyNPC extends NPC {

    private final boolean isQuestGiver;

    public FriendlyNPC(String name) {
        super(name);
        isQuestGiver = false;
    }

    public FriendlyNPC(String name, boolean isQuestGiver) {
        super(name);
        this.isQuestGiver = isQuestGiver;
    }


    public void interactWithPlayer() {
        if (!isQuestGiver) {
            System.out.println("Hello adventurer.");
        } else {
            //Interact with player, give quests osv
        }
    }


}
