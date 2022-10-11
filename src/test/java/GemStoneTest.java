import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GemStoneTest {

    @Test
    public void createGemstoneWithColor(){

        GemStone gemStone = new GemStone(MagicColor.BLUE);
        assertEquals(MagicColor.BLUE, gemStone.getColor());


    }


}
