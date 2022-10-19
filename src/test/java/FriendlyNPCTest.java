import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FriendlyNPCTest {

    @Test
    void testPlayerInteract_WithNonQuestGivingNPC() {
        Player p = new Player();
        FriendlyNPC npc = new FriendlyNPC("Brian");
//        p.interactWithFriendlyNPC();
        assertEquals("Hello Adventurer!", npc.interactWithPlayer());
    }
}
