package item;

import item.armor.Armor;
import item.magic.MagicColor;
import item.weapon.Shield;
import item.weapon.Weapon;

public class DefenceVisitor implements ItemVisitor {

    @Override
    public double visit(Weapon item) {
        return 0.5 * useDefence(item);
    }

    @Override
    public double visit(Armor item) {
        return useDefence(item);    }

    @Override
    public double visit(Shield item) {
        return useDefence(item);    }

    protected double useDefence(Item item) {
        double DEFAULT_COST_OF_USE = 0.5;
        double power = item.getStrength();
        double defencePowerFromStones = item.getStrengthFromStonesOfColor(MagicColor.RED);
        double cost = item.getCostFromStonesOfColor(MagicColor.RED);

        item.setStrength(item.getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100 * power * (1 + (defencePowerFromStones / 100))) / 100.0;
    }
}
