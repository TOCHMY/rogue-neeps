package player;

import item.weapon.Shield;
import item.weapon.Weapon;

import java.util.List;

public class Human extends Player{



    //Kan bara ha one-hand och stav/magi
    //Kan klättra över berg OM dexterity > X och berg.height < 10+(.1*dexterity)
    public Human() {
        super(1,1,5, 50);
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
