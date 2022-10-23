package item;

import item.magic.MagicColor;
import item.weapon.Weapon;

public class AttackVisitor implements ItemVisitor {

    @Override
    public double visit(Weapon item) {
        return useAttack(item);
    }

    protected double useAttack(Item item) {
        double DEFAULT_COST_OF_USE = 0.5;
        double baseStrength = item.getBaseStrength();
        double strengthFromStones = item.getStrengthFromStonesOfColor(MagicColor.BLUE);
        double cost = item.getCostFromStonesOfColor(MagicColor.BLUE);

        item.setBaseStrength(item.getBaseStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100 * baseStrength * (1 + (strengthFromStones / 100.0))) / 100.0;
    }


}
