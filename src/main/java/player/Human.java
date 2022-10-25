package player;

import item.Shield;
import item.Weapon;
import nicoMap.NicoFloor;
import nicoMap.NicoTile;
import nicoMap.Swamp;

import java.util.List;

public class Human extends Player{

    //Kan bara ha one-hand och stav/magi
    //Kan klättra över berg OM dexterity > X och berg.height < 10+(.1*dexterity)
    public Human() {
        super(1,1,5, 50);
    }

    @Override
    public boolean canMove(NicoTile tile) {
        if(tile instanceof NicoFloor){
            return true;
        }
        if(tile instanceof Swamp && stats.getStrength() > 20){
            return true;
        }
        return false;
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
