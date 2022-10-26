package quest;

import npc.FriendlyNPC;
import npc.Pig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Human;
import player.Player;
import util.UserInputAsker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//quest.setInitated() och setCompleted() kallas ibland istället för att gå igenom hela processen att spelaren ska
// acceptera quest av npc.NPC
public class QuestTest {

    private final Player player = new Human();
    private final FriendlyNPC npcKate = new FriendlyNPC("Kate", true);
    private final Pig pig = new Pig();
    private final QuestDatabase qdb = new QuestDatabase();
    private Quest quest = new Quest(0, "");

    @BeforeEach
    void defineQuestAndAssignPigQuestToNPCKate() {
        quest = qdb.getQuest(1);
        npcKate.getQuestFromDatabase(1, qdb);
    }

    @Test
    void testQuestIsCorrectlyAssignedToFriendlyNPC() {
        assertEquals(quest, npcKate.getAssignedQuest());
    }

    @Test
    void testQuestIsInitiatedByPlayer() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        npcKate.setDialog(quest.getQuestDescription());
        System.out.println(npcKate.say());
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);
        assertTrue(quest.isInitiated());
    }

    @Test
    void testQuestNotInitiatedByPlayer() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("n");
        npcKate.askToAcceptQuest(userInputAsker, player);
        assertFalse(quest.isInitiated());
    }

    @Test
    void testAskToAcceptQuest_throwsIllegalInput() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("invalid input");
        assertThrows(IllegalArgumentException.class, () ->
                npcKate.askToAcceptQuest(userInputAsker, player));
    }

    @Test
    void testQuestAddedToPlayerQuestLog() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);
        assertEquals(player.getQuestFromQuestLog(quest), quest);
    }

    @Test
    void testQuestNotAccepted_NotAddedToPlayerQuestLog() {
        assertThrows(IllegalStateException.class, ()
                -> player.addQuestToQuestLog(quest)); // "quest.Quest is not accepted"
    }

    @Test
    void testPlayerAbandonQuest_questIsRemovedFromQuestLog() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);
        player.abandonQuest(quest);
        assertFalse(player.getQuestLog().contains(quest));
    }

    @Test
    void testPlayerAbandonQuest_questDoesNotExistInQuestLog_throwsException() {
        assertThrows(NullPointerException.class, ()
                -> player.abandonQuest(quest)); // "quest.Quest does not exist in quest log"
    }

    @Test
    void testPlayerAbandonQuest_questIsSetToQuestGiver() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);
        player.abandonQuest(quest);
        assertEquals(quest, npcKate.getAssignedQuest());
    }

    @Test
    void testPlayerAbandonQuestAfterKillingEnemyPig_questStatusIsReset() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);
        player.killTarget(pig);
        assertEquals("1 of 5 pigs killed.", quest.printKillQuestStatus());
        player.abandonQuest(quest);
        npcKate.askToAcceptQuest(userInputAsker, player);
        assertEquals("0 of 5 pigs killed.", quest.printKillQuestStatus());
    }

    @Test
    void testPigQuestGoalIsUpdated_WhenPigIsKilled() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        assertEquals("0 of 5 pigs killed.", quest.printKillQuestStatus());
        player.killTarget(pig);
        assertEquals("1 of 5 pigs killed.", quest.printKillQuestStatus());
    }

    //Detta test skulle kunna slås ihop med testKillQuestDoesNotIncrementBeyondQuestGoal().
    //Testar enbart ett av alla olika format ett ord kan ha blir korrekt format
    //I detta fall ett djur vars böjning av ordet är enklet -> lägg till ett s för att representera npc.Pig i plural
    // andra skulle vara tex Octopus -> Octopi, Mantis -> Mantises osv
    @Test
    void testPigQuestStatusTextDisplaysCorrectText_WhenPigsGetKilled_AfterQuestGoalIsCompleted() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig); //6
        assertEquals("5 of 5 pigs killed.", quest.printKillQuestStatus());
    }

    //Testar enbart ett av alla olika format ett ord kan ha blir korrekt format (i detta fall djur med s i slutet)
    // andra skulle vara tex Octopus -> Octopi osv

    @Test
    void testKillQuestDoesNotIncrementBeyondQuestGoal() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig); //6 pigs killed
        assertEquals(5, quest.getKillQuestCurrentKilled());
    }

    @Test
    void testPigQuestGoalIsSetToCompleted_WhenQuestGoalIsReached() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        assertFalse(quest.isCompleted());
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        player.killTarget(pig);
        assertTrue(quest.isCompleted());
    }

    @Test
    void testPigQuestReturnedToFriendlyNPC_questIsRemovedFromQuestLog() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        quest.setCompleted(true);
        npcKate.completeQuest(quest, player);
        assertFalse(player.getQuestLog().contains(quest));
    }

    @Test
    void testPigQuestReturnedToFriendlyNPC_questCanNotBeAcceptedAgain() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        quest.setCompleted(true);
        npcKate.completeQuest(quest, player);
        assertNull(npcKate.getAssignedQuest());
    }

    @Test
    void testPigQuestReturnedToFriendlyNPC_questIsRemovedFromQuestLog_withNPCInteraction() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Talk to " + npcKate.getName() + "? y / n")).thenReturn("y");
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        quest.setCompleted(true);
        assertFalse(quest.isReturnedToQuestGiver());
        player.interactWithFriendlyNPC(userInputAsker, npcKate);
        assertTrue(quest.isReturnedToQuestGiver());
        assertTrue(player.getFinishedQuestsLog().contains(quest));
        assertFalse(player.getQuestLog().contains(quest));
    }

    @Test
    void testPigQuest_NotCompleted_questRemainsInQuestLog() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        npcKate.completeQuest(quest, player);
        assertTrue(player.getQuestLog().contains(quest));
    }

    @Test
    void testPigQuest_whenReturnedToQuestGiver_questAddedToFinishedQuestLog() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        quest.setCompleted(true);
        npcKate.completeQuest(quest, player);
        assertTrue(player.getFinishedQuestsLog().contains(quest));
    }


    //Herbert Quest
    @Test
    void testHerbertQuestName() {
        Quest quest2 = qdb.getQuest(2);
        assertEquals("Find Herbert", quest2.getQuestName());
    }

    @Test
    void testHerbertQuest_isCompletedWhenHerbertIsFound() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        Quest quest2 = qdb.getQuest(2);
        FriendlyNPC npcHerbert = quest2.getTalkQuestTarget();
        npcHerbert.setDialog("Oh I am so lost... You found me! I will return to " + npcKate.getName() + " now.");
        System.out.println(npcHerbert.say());
        npcHerbert.setQuestGoal();
        quest2.setQuestGiver(npcKate);
        quest2.setInitiated(true);
        player.addQuestToQuestLog(quest2);
        assertFalse(quest2.isCompleted());
        when(userInputAsker.ask("Talk to " + npcHerbert.getName() + "? y / n")).thenReturn("y");
        player.interactWithFriendlyNPC(userInputAsker, npcHerbert);
        assertTrue(quest2.isCompleted());
    }

}
