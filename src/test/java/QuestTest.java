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

    //Detta test skulle kunna slås ihop med testKillQuestDoesNotIncrementBeyondQuestGoal()
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


    //Herbert Quest
    @Test
    void testHerbertQuestName() {
        Quest quest2 = qdb.getQuest(2);
        assertEquals("Find Herbert", quest2.getQuestName());
    }

}
