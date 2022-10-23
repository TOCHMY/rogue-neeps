package npc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class NPCTest {

   /* //Hur testa random movement? Kolla med Henke B

    @Test
    void testIdleMovement_makeSureTheNPCMoved() {
        npc.NPC randy = new npc.NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
        randy.idleMove();
        assertNotEquals(randy.getCurrentPosition(), new int[]{50, 50});
    }

    @Test
    void testUpMovement() {
        npc.NPC randy = new npc.NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
        randy.moveUp();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 51});
        randy.moveUp();
        randy.moveUp();
        randy.moveUp();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 54});
    }
    @Test
    void testDownMovement() {
        npc.NPC randy = new npc.NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
        randy.moveDown();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 49});
    }
    @Test
    void testLeftMovement() {
        npc.NPC randy = new npc.NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50,50});
        randy.moveLeft();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{49, 50});
    }

    @Test
    void testRightMovement() {
        npc.NPC randy = new npc.NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50,50});
        randy.moveRight();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{51, 50});
    }

    @Test
    void testMovement_moveInACircle() {
        npc.NPC randy = new npc.NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
        randy.moveUp();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 51});
        randy.moveRight();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{51, 51});
        randy.moveDown();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{51, 50});
        randy.moveLeft();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
    }*/
}
