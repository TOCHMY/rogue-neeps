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
    Position tunnelPosition;

    @BeforeEach
    public void initEach(){
        human = new Human();
        map = new Map();
        human.setMap(map);
        roomA = new Room("A", 27,8, new Tile(2,7), map);
        map.addRoom(roomA);

        Room roomC = new Room("C", 23,6, new Tile(new Position(14,26)), map);
        map.addRoom(roomC);

        Tile tunnelStart = map.getMap()[10][31];
        Tile tunnelEnd = map.getMap()[13][31];
        Tunnel tunnel1 = new Tunnel("A", "C",tunnelStart,tunnelEnd);
        map.addTunnel(tunnel1);

        tunnelPosition = tunnelStart.getPosition();

        tileToTest = new Position(3, 9);
    }

    @Test
    public void Human_StrBelow10_FloorTile_Expect_True(){
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    public void Human_StrAbove10Below20_FloorTile_Expect_True(){
        human.getStats().addStrength(10);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    public void Human_StrAbove20_FloorTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    public void Human_StrBelow10_TunnelTile_Expect_True(){
        assertTrue(human.canMove(map.getTile(tunnelPosition)));
    }
    @Test
    public void Human_StrAbove10Below20_TunnelTile_Expect_True(){
        human.getStats().addStrength(10);
        assertTrue(human.canMove(map.getTile(tunnelPosition)));
    }
    @Test
    public void Human_StrAbove20_TunnelTile_Expect_True(){
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tunnelPosition)));
    }

    @Test
    public void Human_StrBelow10_SwampTile_Expect_True(){
        roomA.addSwampTile(tileToTest);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    public void Human_StrAbove10Below20_SwampTile_Expect_True(){
        roomA.addSwampTile(tileToTest);
        human.getStats().addStrength(10);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    public void Human_StrAbove20_SwampTile_Expect_True(){
        roomA.addSwampTile(tileToTest);
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }


    @Test
    public void Human_StrBelow10_WaterTile_Expect_False(){
        roomA.addWaterTile(tileToTest);
        assertFalse(human.canMove(map.getTile(tileToTest)));
    }

    @Test
    public void Human_StrAbove10Below20_WaterTile_Expect_True(){
        roomA.addWaterTile(tileToTest);
        human.getStats().addStrength(10);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }
    @Test
    public void Human_StrAbove20_WaterTile_Expect_True(){
        roomA.addWaterTile(tileToTest);
        human.getStats().addStrength(21);
        assertTrue(human.canMove(map.getTile(tileToTest)));
    }
}
