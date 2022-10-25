package quest;

import npc.Albatross;
import npc.EnemyNPC;
import npc.FriendlyNPC;
import npc.Pig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Human;
import player.Player;
import util.UserInputAsker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestTestStateMachineForKillQuest {

    //Initial Setup
    private final Player player = new Human();
    private final QuestDatabase qdb = new QuestDatabase();
    private final FriendlyNPC npcKate = new FriendlyNPC("Kate", true);
    private Quest quest = new Quest(0, "");


    @Test
    @DisplayName("State machine test #1: Player accepts quest with a goal to kill more than one enemy. Quest goal is achieved and the quest is returned to quest giver NPC")
    void completeQuestAsNormal() {
        Pig pig = new Pig();

        //State A - Quest exists at questGiver
        quest = qdb.getQuest(4); //Quest: "Kill Two Pigs"
        npcKate.getQuestFromDatabase(quest.getQuestID(), qdb); //Set npcKate as quest giver for quest
        assertEquals(quest, npcKate.getAssignedQuest());

        // Edge 1 - Player accepts quest
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);

        // State B - Quest accepted, quest exists in player questlog
        assertTrue(quest.isInitiated());
        assertTrue(player.getQuestLog().contains(quest));

        // Edge 2 - Player kills enemy, quest goal not achieved
        player.killTarget(pig);
        assertNotEquals(quest.getKillQuestCurrentKilled(), quest.getAmountOfEnemiesToKill());

        // Edge 3 - Player kills enemy, quest goal is achieved
        player.killTarget(pig);
        assertEquals(quest.getKillQuestCurrentKilled(), quest.getAmountOfEnemiesToKill());

        // State C - Quest is completed
        assertTrue(quest.isCompleted());

        // Edge 4 - Player returns quest to quest giver
        when(userInputAsker.ask("Talk to " + npcKate.getName() + "? y / n")).thenReturn("y");
        player.interactWithFriendlyNPC(userInputAsker, npcKate);

        // State D - Quest is returned to quest giver, thus the quest is finished
        assertTrue(quest.isReturnedToQuestGiver());
        assertFalse(player.getQuestLog().contains(quest));
        assertTrue(player.getFinishedQuestsLog().contains(quest));
    }

    @Test
    @DisplayName("State machine test #2: Player accepts a quest and immediately abandons the quest")
    void playerAcceptsQuest_thenAbandonsTheQuest() {

        //State A - Quest exists at questGiver
        quest = qdb.getQuest(4); //Quest: "Kill Two Pigs"
        npcKate.getQuestFromDatabase(quest.getQuestID(), qdb); //Set npcKate as quest giver for quest
        assertEquals(quest, npcKate.getAssignedQuest());

        // Edge 1 - Player accepts quest
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);

        // State B - Quest accepted, quest exists in player questlog
        assertTrue(quest.isInitiated());
        assertTrue(player.getQuestLog().contains(quest));

        // Edge 5 - Player abandons quest
        player.abandonQuest(quest);

        // State E - Quest abandoned
        assertFalse(player.getQuestLog().contains(quest));
        assertFalse(quest.isInitiated());
    }

    @Test
    @DisplayName("State machine test #3: Player accepts a quest, completes the quest goal and then abandons the quest")
    void playerAcceptsAndCompleteQuest_thenAbandonsTheQuest() {
        Albatross albatross = new Albatross();
        //State A - Quest exists at questGiver
        quest = qdb.getQuest(3); //Quest: "Hungry Albatross"
        npcKate.getQuestFromDatabase(quest.getQuestID(), qdb); //Set npcKate as quest giver for quest
        assertEquals(quest, npcKate.getAssignedQuest());

        // Edge 1 - Player accepts quest
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);

        // State B - Quest accepted, quest exists in player questlog
        assertTrue(quest.isInitiated());
        assertTrue(player.getQuestLog().contains(quest));

        // Edge 3 - Player kills enemy, quest goal is achieved
        player.killTarget(albatross);
        assertEquals(quest.getKillQuestCurrentKilled(), quest.getAmountOfEnemiesToKill());

        // State C - Quest is completed
        assertTrue(quest.isCompleted());

        // Edge 6 - Player abandons quest
        player.abandonQuest(quest);

        // State E - Quest abandoned
        assertFalse(player.getQuestLog().contains(quest));
        assertFalse(quest.isInitiated());
        assertFalse(quest.isCompleted());

    }

}
