import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FriendlyNPCTest {


    @Test
    void testNonQuestGiverFriendlyNPC_respondsToPlayerInteraction() {
        FriendlyNPC npc = new FriendlyNPC("Brian");
        assertEquals("Hello adventurer.", npc.nonQuestGiverResponse());
    }

    //Return QuestDescription for particular quest
    @Test
    void testQuestGiverFriendlyNPC_respondsToPlayerInteraction_ExpectPigQuestDescription() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        npc.assignQuestToNPC(1);
        assertEquals("The pigs in this area has developed an attitude. Show them who's boss!", npc.respondWithQuest());
    }

    @Test
    void testFriendlyNPC_AsksPlayerToAcceptQuest() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
    }
}
