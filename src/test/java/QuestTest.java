import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestTest {

    //Ett quest bör ha en unInitiated State, en initiated state och en completed state
    @Test
    void testQuestNotInitiatedByPlayer() {
        Quest quest = new Quest();
        assertFalse(quest.isInitiated);
    }

    @Test
    void testQuestArrayListLength() {
        Quest quest = new Quest();
        assertEquals(3, quest.questArrayList.size());
    }
    @Test
    void testGetQuestByID() {
        Quest quest = new Quest();
        quest = quest.getQuestByID(1);
        assertEquals(quest, quest.questArrayList.get(1));
    }

    @Test
    void testGetQuestFromMapByID() {
        Quest quest = new Quest();
        quest = quest.getQuestByIDFromHashMap(1);
        assertEquals(quest, quest.questHashMap.get(1));
    }

    @Test
    void testQuestIsInitiatedByPlayer() {
        Quest quest = new Quest();
        quest.acceptQuest("y");
        assertTrue(quest.isInitiated);
    }

    @Test
    void testQuestAddedToPlayerQuestLog() {
        Quest quest = new Quest();
        Player player = new Player();
        quest.acceptQuest("y");
        player.addQuestToQuestLog(quest);
        assertEquals(player.getQuestFromQuestLog(quest.questID), quest);
    }

    @Test
    void testQuestNotAccepted_NotAddedToPlayerQuestLog() {
        Quest quest = new Quest();
        Player player = new Player();
        assertThrows(IllegalStateException.class, ()
                -> player.addQuestToQuestLog(quest));
    }

    @Test
    void testPigQuestName() {
        Quest quest = new Quest();
        assertEquals("Pig Menace", quest.getQuestName(1));
    }

    @Test
    void testPigHash() {
        Quest quest = new Quest();
        assertEquals("Pig Menace", quest.getQuestHashName(1));
    }
    @Test
    void testPigQuestDescription() {
        Quest quest = new Quest();
        assertEquals("The pigs in this area has developed an attitude. Show them who's boss!", quest.getQuestDescription(1));
    }

    @Test
    void testHerbertQuestName() {
        Quest quest = new Quest();
        assertEquals("Find Herbert", quest.getQuestName(2));
    }

    @Test
    void testQuestGoalExists() {
        Quest quest = new Quest();
        assertEquals("Find Herbert", quest.getQuestGoal(2));
    }

    @Test
    void testQuestCompletion_whenQuestGoalIsReached() {
        Quest quest = new Quest();
//        quest = quest.getQuest
    }

}
