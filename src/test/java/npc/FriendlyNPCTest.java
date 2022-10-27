package npc;

import org.junit.jupiter.api.Test;
import player.Human;
import player.Player;
import quest.Quest;
import quest.QuestDatabase;
import util.UserInputAsker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FriendlyNPCTest {

    //Testar inte completeQuest() här för att completeQuest() testas i QuestTest

    private final QuestDatabase qdb = new QuestDatabase();
    Player player = new Human();

    //Return QuestDescription for particular quest
    @Test
    void testQuestGiverFriendlyNPC_respondsToPlayerInteraction_expectPigQuestDescription() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        String questDescription = qdb.getQuest(1).getQuestDescription();
        npc.getQuestFromDatabase(1, qdb);
        assertEquals(questDescription, npc.respondWithQuest());
    }

    @Test
    void testFriendlyNPC_questIsAssignedToFriendlyNPC() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        npc.getQuestFromDatabase(1, qdb);
        assertEquals(qdb.getQuest(1), npc.getAssignedQuest());
    }

    @Test
    void testFriendlyNPC_isQuestGiver() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        assertTrue(npc.isQuestGiver());
    }

    @Test
    void testFriendlyNPC_hasCorrectName() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        assertEquals("Kate", npc.getName());
    }

    @Test
    void testFriendlyNPC_hasCorrectDialog() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        npc.getQuestFromDatabase(1, qdb);
        assertEquals(qdb.getQuest(1).getQuestDescription(), npc.say());
    }

    @Test
    void testFriendlyNPC_asksPlayerToAcceptQuest_expectAccept() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        npc.getQuestFromDatabase(1, qdb);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        assertEquals("Thank you for helping me!", npc.askToAcceptQuest(userInputAsker, player));
    }
    @Test
    void testFriendlyNPC_asksPlayerToAcceptQuest_expectNotAccept() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        npc.getQuestFromDatabase(1, qdb);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("n");
        assertEquals("Oh, okay...", npc.askToAcceptQuest(userInputAsker, player));
    }
    @Test
    void testFriendlyNPC_invalidInput() {
        FriendlyNPC npc = new FriendlyNPC("Kate", true);
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        npc.getQuestFromDatabase(1, qdb);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("invalid input");
        assertThrows(IllegalArgumentException.class,  () -> npc.askToAcceptQuest(userInputAsker, player));
    }


    @Test
    void testFriendlyNPC_isQuestGoal() {
        FriendlyNPC npc = new FriendlyNPC("Herbert");
        npc.setQuestGoal();
        assertTrue(npc.isQuestGoal());
    }
}
