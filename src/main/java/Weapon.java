import java.util.List;

public class Weapon extends Item {



    public Weapon(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }


    @Override
    public void die() {
    }
}
