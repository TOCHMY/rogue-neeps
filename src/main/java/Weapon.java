import java.util.List;

public class Weapon extends Item {

    public Weapon(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }

    public double attack(){
        double DEFAULT_COST_OF_USE = 0.5;
        double power = getStrength();
        double attackPowerFromStones = getStrengthFromStonesOfColor(MagicColor.BLUE);
        double cost = getCostFromStonesOfColor(MagicColor.BLUE);

        setStrength(getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(power * (1 + (attackPowerFromStones / 100)));
    }
}
