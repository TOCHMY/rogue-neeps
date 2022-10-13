import java.util.List;

public class Weapon extends Item {
    private final double DEFAULT_COST_OF_USE = 0.5;

    public Weapon(double strength, List<MagicSocket> sockets){
        super(strength, sockets);
    }

    public double attack(){

        double power = getStrength();
        double attackPowerFromStones = getStrengthFromStonesOfColor(MagicColor.BLUE);
        double cost = getCostFromStonesOfColor(MagicColor.BLUE);

        setStrength(getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100 * power * (1 + (attackPowerFromStones / 100.0)))/100.0;
    }

    public double defend() {
        double power = getStrength();
        double defencePowerFromStones = getStrengthFromStonesOfColor(MagicColor.RED);
        double cost = getCostFromStonesOfColor(MagicColor.RED);

        setStrength(getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100*power * (1 + (defencePowerFromStones / 100)))/100.0;
    }
}
