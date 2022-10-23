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
        double power = item.getStrength();
        double attackPowerFromStones = item.getStrengthFromStonesOfColor(MagicColor.BLUE);
        double cost = item.getCostFromStonesOfColor(MagicColor.BLUE);

        item.setStrength(item.getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100 * power * (1 + (attackPowerFromStones / 100.0))) / 100.0;
    }


}
