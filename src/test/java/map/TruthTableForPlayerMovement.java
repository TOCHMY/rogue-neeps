package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import player.Human;
import player.Player;
import util.Position;

import static org.junit.jupiter.api.Assertions.*;

public class TruthTableForPlayerMovement {
    Player human;
    Map map;
    Room roomA;
    Position tileToTest;

    @BeforeEach
    public void initEach(){
        human = new Human();
        map = new Map();
        human.setMap(map);
        roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);
        tileToTest = new Position(3, 9);
    }


    @Test
    public void Human_StrBelow10_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    public void Human_StrAbove10_Below_20_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        human.getStats().addStrength(10);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    public void Human_StrAbove20_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        human.getStats().addStrength(10);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }
}
