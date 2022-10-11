import java.util.List;

public abstract class Item {

    private double strength;
    private List<MagicSocket> sockets;

    public Item(double strength, List<MagicSocket> sockets) {
        if(strength < 1 || strength > 100)
            throw new IllegalArgumentException("Strength must be between 1 to 100");
        if(sockets.isEmpty() || sockets.size() > 5)
            throw new IllegalArgumentException("Number of sockets must be between 1 to 5");
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
