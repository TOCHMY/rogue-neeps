import java.util.List;

public class Weapon extends Item {

    public Weapon(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }

    public double attack(){
       return super.attackImplementation();
    }

    public double defend() {
        return super.defendImplementation();
    }
}
