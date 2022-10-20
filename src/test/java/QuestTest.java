import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Tänkte göra tillståndsmaskin på QuestTest, fråga till Henke -> När man skriver testfallen till tillståndsmaskinen
// ska alla test vara samlade i samma Testklass, eller kan man hoppa mellan? Huruvida ett quest accepteras eller ej styrs
// i FriendlyNPC klassen nu, så tillstånd styrs i QuestTest medan bågar kan styras i FriendlyNPC och EnemyNPC


//quest.setInitated() och setCompleted() kallas ibland istället för att gå igenom hela processen att spelaren ska
// acceptera quest av NPC
public class QuestTest {

    Player player = new Human();
    private final FriendlyNPC npcKate = new FriendlyNPC("Kate", true);
    private Quest quest = new Quest(0, "");
    private final QuestDatabase qdb = new QuestDatabase();

    @BeforeEach
    void assignPigQuestToNPCKate() {
        quest = qdb.getQuest(1);
        npcKate.assignQuestToNPC(quest);
    }


    @Test
    void testQuestIsCorrectlyAssignedToFriendlyNPC() {
        assertEquals(quest, npcKate.getAssignedQuest());
    }

    @Test
    void testQuestIsInitiatedByPlayer() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
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

    //gör assertThrows av asktoaccept
//    void testQuest
    @Test
    void testQuestAddedToPlayerQuestLog() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);
        assertEquals(player.getQuestFromQuestLog(quest.getQuestID()), quest);
    }

    @Test
    void testQuestNotAccepted_NotAddedToPlayerQuestLog() {
        assertThrows(IllegalStateException.class, ()
                -> player.addQuestToQuestLog(quest));
    }


    @Test
    void testPigQuestGoalIsUpdated_WhenPigsGetKilled() {
        EnemyNPC pig = new EnemyNPC("Pig", 1, true);
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        assertEquals("0 of 5 pigs killed.", quest.printKillQuestStatus());
        player.killTarget(pig);
        assertEquals("1 of 5 pigs killed.", quest.printKillQuestStatus());
    }

    //Detta test skulle kunna slås ihop med testKillQuestDoesNotIncrementBeyondQuestGoal().
    //Testar enbart ett av alla olika format ett ord kan ha blir korrekt format
    //I detta fall ett djur vars böjning av ordet är enklet -> lägg till ett s för att representera Pig i plural
    // andra skulle vara tex Octopus -> Octopi, Mantis -> Mantises osv
    @Test
    void testPigQuestStatusTextDisplaysCorrectText_WhenPigsGetKilled_AfterQuestGoalIsCompleted() {
        EnemyNPC pig = new EnemyNPC("Pig", 1, true);
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
        EnemyNPC pig = new EnemyNPC("Pig", 1, true);
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
        EnemyNPC pig = new EnemyNPC("Pig", 1, true);
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
    void testPigQuestReturnedToFriendlyNPC_questIsCompleted_questIsRemovedFromQuestLog() {
        quest.setInitiated(true);
        player.addQuestToQuestLog(quest);
        quest.setCompleted(true);
        npcKate.completeQuest(quest, player);
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
    void testPigQuest_whenCompleted_questAddedToFinishedQuestLog() {
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

/*    @Test
    void testHerbertQuest_isCompletedWhenHerbertIsFound() {
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        Quest quest2 = qdb.getQuest(2);
        FriendlyNPC npcHerbert = new FriendlyNPC("Herbert");
        quest2.setQuestGiver(npcKate);
        quest2.setInitiated(true);
        player.addQuestToQuestLog(quest2);
        assertFalse(quest2.isCompleted());
        when(userInputAsker.ask("Talk to " + npcHerbert.getName() + "? y / n")).thenReturn("y");
        player.interactWithFriendlyNPC(userInputAsker, npcHerbert);
        assertTrue(quest2.isCompleted());
    }*/

}
