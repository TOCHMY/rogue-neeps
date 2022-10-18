public class FriendlyNPC extends NPC {

    private final boolean isQuestGiver;

   // public FriendlyNPC(String name, int xPosition, int yPosition) {
        public FriendlyNPC(String name) {
       // super(name, xPosition, yPosition);
        super(name);
        isQuestGiver = false;
    }

    public FriendlyNPC(String name, int xPosition, int yPosition, boolean isQuestGiver) {
       // super(name, xPosition, yPosition);
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
