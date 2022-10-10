import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class NPCtest {

    //Hur testa random movement? Kolla med Henke B

    @Test
    void testIdleMovement_makeSureTheNPCMoved() {
        NPC randy = new NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
        randy.idleMove();
        assertNotEquals(randy.getCurrentPosition(), new int[]{50, 50});
    }

    @Test
    void testUpMovement() {
        NPC randy = new NPC("Randy", 50, 50);
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
        NPC randy = new NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
        randy.moveDown();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 49});
    }
    @Test
    void testLeftMovement() {
        NPC randy = new NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50,50});
        randy.moveLeft();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{49, 50});
    }

    @Test
    void testRightMovement() {
        NPC randy = new NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50,50});
        randy.moveRight();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{51, 50});
    }

    @Test
    void testMovement_moveInACircle() {
        NPC randy = new NPC("Randy", 50, 50);
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
        randy.moveUp();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 51});
        randy.moveRight();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{51, 51});
        randy.moveDown();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{51, 50});
        randy.moveLeft();
        assertArrayEquals(randy.getCurrentPosition(), new int[]{50, 50});
    }
}
