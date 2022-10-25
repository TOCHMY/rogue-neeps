package quest;

import npc.FriendlyNPC;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import player.Human;
import player.Player;
import util.UserInputAsker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestTestStateMachineForTalkQuest {
    //Initial Setup
    private final Player player = new Human();
    private final QuestDatabase qdb = new QuestDatabase();
    private final FriendlyNPC npcKate = new FriendlyNPC("Kate", true);
    private final Quest quest = qdb.getQuest(2); // Quest: "Find Herbert"
    private final FriendlyNPC npcHerbert = quest.getTalkQuestTarget();


    @Test
    @DisplayName("Player accepts quest with a goal to speak to a different NPC. Quest goal is achieved and the quest is returned to the quest giver NPC")
    void completeQuestAsNormal() {

        // State A - Quest exists at quest giver
        npcKate.assignQuestToNPC(quest);
        assertEquals(quest, npcKate.getAssignedQuest());
        assertEquals(npcKate, quest.getQuestGiver());

        // Edge 1 - Player accepts quest
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);

        // State B - Quest accepted, quest exists in player questlog
        assertTrue(quest.isInitiated());
        assertTrue(player.getQuestLog().contains(quest));

        // Edge 2 - Player talks to target NPC
        when(userInputAsker.ask("Talk to " + npcHerbert.getName() + "? y / n")).thenReturn("y");
        player.interactWithFriendlyNPC(userInputAsker, npcHerbert);

        // State C - Quest is completed
        assertTrue(quest.isCompleted());

        // Edge 3 - Player returns quest to quest giver
        when(userInputAsker.ask("Talk to " + npcKate.getName() + "? y / n")).thenReturn("y");
        player.interactWithFriendlyNPC(userInputAsker, npcKate);

        // State D - Quest returned to quest giver, thus quest is finished
        assertTrue(quest.isReturnedToQuestGiver());
        assertFalse(player.getQuestLog().contains(quest));
        assertTrue(player.getFinishedQuestsLog().contains(quest));
    }

    @Test
    @DisplayName("Player accepts a quest and immediately abandons the quest")
    void playerAcceptsQuest_thenAbandonsTheQuest() {

        // State A - Quest exists at quest giver
        npcKate.assignQuestToNPC(quest);
        assertEquals(quest, npcKate.getAssignedQuest());
        assertEquals(npcKate, quest.getQuestGiver());

        // Edge 1 - Player accepts quest
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);

        // State B - Quest accepted, quest exists in player questlog
        assertTrue(quest.isInitiated());
        assertTrue(player.getQuestLog().contains(quest));

        // Edge 4 - Player abandons quest
        player.abandonQuest(quest);

        // State E - Quest abandoned
        assertFalse(player.getQuestLog().contains(quest));
        assertFalse(quest.isInitiated());

    }

    @Test
    @DisplayName("Player accepts a quest, completes the quest goal and then abandons the quest")
    void playerAcceptsAndCompleteQuest_thenAbandonsTheQuest() {

        // State A - Quest exists at quest giver
        npcKate.assignQuestToNPC(quest);
        assertEquals(quest, npcKate.getAssignedQuest());
        assertEquals(npcKate, quest.getQuestGiver());

        // Edge 1 - Player accepts quest
        UserInputAsker userInputAsker = mock(UserInputAsker.class);
        when(userInputAsker.ask("Do you accept this quest? y / n")).thenReturn("y");
        npcKate.askToAcceptQuest(userInputAsker, player);

        // State B - Quest accepted, quest exists in player questlog
        assertTrue(quest.isInitiated());
        assertTrue(player.getQuestLog().contains(quest));

        // Edge 2 - Player talks to target NPC
        when(userInputAsker.ask("Talk to " + npcHerbert.getName() + "? y / n")).thenReturn("y");
        player.interactWithFriendlyNPC(userInputAsker, npcHerbert);

        // State C - Quest is completed
        assertTrue(quest.isCompleted());

        // Edge 5 - Player abandons quest
        player.abandonQuest(quest);

        // State E - Quest abandoned
        assertFalse(player.getQuestLog().contains(quest));
        assertFalse(quest.isInitiated());
        assertFalse(quest.isCompleted());
    }
}
