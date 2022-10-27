package util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void TestPositiveValues(){
        assertDoesNotThrow(() -> new Position(3,5));
    }

    @Test
    public void TestZeroValues(){
        assertDoesNotThrow(() -> new Position(0,0));
    }

    @Test
    public void TestNegativeValuesExpectThrows(){
        assertThrows(IllegalArgumentException.class, () -> new Position(-1,0));
    }

    @Test void ExpectCorrectValuesFromGet(){
        int rowVal = 2;
        int colVal = 5;
        Position position = new Position(rowVal,colVal);

        assertEquals(2, position.row());
        assertEquals(5, position.col());
    }

    @Test void WhenPositionNegativeExpectThrows(){
        Position pos = new Position(0,0);
        assertThrows(IllegalArgumentException.class, () -> pos.newPosition(Direction.UP));
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"RIGHT", "LEFT", "DOWN", "UP"})
    void ExpectCorrectPosition(Direction direction) {
        Position pos = new Position(2,2);
        assertDoesNotThrow(() -> pos.newPosition(direction));
    }
}
