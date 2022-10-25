package player;


import item.items.Shield;
import item.items.Weapon;
import map.Tile;


import java.util.List;

public class Human extends Player{

    //Kan bara ha one-hand och stav/magi
    //Kan klättra över berg OM dexterity > X och berg.height < 10+(.1*dexterity)
    public Human() {
        super(1,1,5, 50);
    }

    @Override
    public boolean canMove(Tile tile) {
        return true;

    }

    @Override
    void equip(Weapon weapon) {

    }
    void equip(Shield shield) {

    }

    @Override
    List<Weapon> canEquip() {
        return null;
    }
}
