import java.util.List;

public abstract class Item {

    private double strength;
    private List<MagicSocket> sockets;

    public Item(double strength, List<MagicSocket> sockets) {
        if(strength < 1 || strength > 100)
            throw new IllegalArgumentException("Strength must be between 1 and 100");
        if(sockets.size() > 5)
            throw new IllegalArgumentException("Strength must be between 1 and 100");
        this.strength = strength;
        this.sockets = sockets;
    }

    double getStrength(){
        return this.strength;
    }

    public List<MagicSocket> getSockets() {
        return sockets;
    }

    void die(){}


}
