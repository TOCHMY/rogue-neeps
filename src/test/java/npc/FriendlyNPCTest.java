package npc;

import npc.FriendlyNPC;
import org.junit.jupiter.api.Test;
import player.Human;
import player.Player;
import quest.QuestDatabase;
import util.UserInputAsker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendlyNPCTest {

    private final QuestDatabase qdb = new QuestDatabase();
    Player player = new Human();

    @Test
    void testNonQuestGiverFriendlyNPC_respondsToPlayerInteraction() {
        FriendlyNPC npc = new FriendlyNPC("Brian");
        assertEquals("Hello adventurer.", npc.nonQuestGiverResponse());
    }


    //Return QuestDescription for particular quest
    @Test
    void testQuestGiverFriendlyNPC_respondsToPlayerInteraction_ExpectPigQuestDescription() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        npc.getQuestFromDatabase(1, qdb);
        assertEquals("The pigs in this area has developed an attitude. Show them who's boss!", npc.respondWithQuest());
    }

    @Test
    void testFriendlyNPC_AsksPlayerToAcceptQuest_ExpectAccept() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        npc.getQuestFromDatabase(1, qdb);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        assertEquals("Thank you for helping me!", npc.askToAcceptQuest(userInputAsker, player));
    }
    @Test
    void testFriendlyNPC_AsksPlayerToAcceptQuest_ExpectNotAccept() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        UserInputAsker userInputAsker = mock(UserInputAsker.class);

        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("n");
        assertEquals("Oh, okay...", npc.askToAcceptQuest(userInputAsker, player));
    }
    @Test
    void testFriendlyNPC_InvalidInput() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        npc.getQuestFromDatabase(1, qdb);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("invalid input");
        assertThrows(IllegalArgumentException.class,  () -> npc.askToAcceptQuest(userInputAsker, player));
    }
}