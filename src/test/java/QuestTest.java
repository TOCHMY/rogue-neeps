import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//Tänkte göra tillståndsmaskin på QuestTest, fråga till Henke -> När man skriver testfallen till tillståndsmaskinen
// ska alla test vara samlade i samma Testklass, eller kan man hoppa mellan? Huruvida ett quest accepteras eller ej styrs
// i FriendlyNPC klassen nu

public class QuestTest {
    QuestDatabase qdb = new QuestDatabase();

    @Test
    void testQuestNotInitiatedByPlayer() {
        Quest quest = qdb.getQuest(1);
        assertFalse(quest.isInitiated());
    }

//    @Test
//    void testQuestIsInitiatedByPlayer() {
//        Quest quest = qdb.getQuest(1);
//        FriendlyNPC npc = new FriendlyNPC("Kate", true);
//        UserInputAsker userInputAsker = mock(UserInputAsker.class);
//        npc.getQuestFromDatabase(quest.getQuestID());
//        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
//        npc.askToAcceptQuest(userInputAsker);
//
//        assertTrue(quest.isInitiated());
//    }
//
//    @Test
//    void testQuestAddedToPlayerQuestLog() {
//        Quest quest = new Quest();
//        Player player = new Player();
//        quest.acceptQuest("y");
//        player.addQuestToQuestLog(quest);
//        assertEquals(player.getQuestFromQuestLog(quest.questID), quest);
//    }
//
//    @Test
//    void testQuestNotAccepted_NotAddedToPlayerQuestLog() {
//        Quest quest = new Quest();
//        Player player = new Player();
//        assertThrows(IllegalStateException.class, ()
//                -> player.addQuestToQuestLog(quest));
//    }
//
//    @Test
//    void testPigQuestName() {
//        Quest quest = new Quest();
//        assertEquals("Pig Menace", quest.getQuestName(1));
//    }
//
//    @Test
//    void testPigHash() {
//        Quest quest = new Quest();
//        assertEquals("Pig Menace", quest.getQuestHashName(1));
//    }
//    @Test
//    void testPigQuestDescription() {
//        Quest quest = new Quest();
//        assertEquals("The pigs in this area has developed an attitude. Show them who's boss!", quest.getQuestDescription(1));
//    }
//
//    @Test
//    void testHerbertQuestName() {
//        Quest quest = new Quest();
//        assertEquals("Find Herbert", quest.getQuestName(2));
//    }
//
//    @Test
//    void testQuestGoalExists() {
//        Quest quest = new Quest();
//        assertEquals("Find Herbert", quest.getQuestGoal(2));
//    }
//
//    @Test
//    void testQuestCompletion_whenQuestGoalIsReached() {
//        Quest quest = new Quest();
////        quest = quest.getQuest
//    }
//
}
