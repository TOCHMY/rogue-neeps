package player;

import item.items.Shield;
import item.items.Weapon;
import map.Tile;


import java.util.List;

public class Ogre extends Player{

    //kan bara ha melee vapen

    //Kan slå sönder vägg OM vapen är av brute och strength >= x för väggtyp Y
    //Testfall enligt beslutstabell för var1-n och res klättra, slå sönder

    public Ogre() {
        super(5, 1, 1, 100);
    }


    @Override
    public boolean canMove(Tile tile) {
        return true;
    }

    @Override
    void equip(Weapon weapon){

    }
    @Override
    void equip(Shield shield) {

    }
    @Override
    List<Weapon> canEquip() {
        return null;
    }
}

