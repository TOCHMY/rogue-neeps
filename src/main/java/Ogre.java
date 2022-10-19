public class Ogre extends Player{

    //kan bara ha melee vapen


    Weapon left_hand;
    Weapon right_hand;

    Ogre() {
        super(5, 1, 1, 100);
    }


    @Override
    void wield(Weapon w){
    }
}

