public class FriendlyNPC extends NPC {

    private final boolean isQuestGiver;

    public FriendlyNPC(String name, int xPosition, int yPosition) {
        super(name, xPosition, yPosition);
        isQuestGiver = false;
    }

    public FriendlyNPC(String name, int xPosition, int yPosition, boolean isQuestGiver) {
        super(name, xPosition, yPosition);
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
