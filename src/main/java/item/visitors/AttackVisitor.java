package item.visitors;

import item.items.Item;
import item.stonesystem.MagicColor;
import item.items.Weapon;

public class AttackVisitor implements ItemVisitor {

    @Override
    public double visit(Weapon item) {
        return useAttack(item);
    }

    private double useAttack(Item item) {
        double attackStrength = getAttackStrength(item);
        subtractCost(item);

        return attackStrength;
    }

    private double getAttackStrength(Item item) {
        double baseStrength = item.getBaseStrength();
        double strengthFromStones = item.getStrengthFromStonesOfColor(MagicColor.BLUE);
        double stoneStrengthFactor = 1 + (strengthFromStones/100);

        return Math.round(100 * baseStrength * stoneStrengthFactor) / 100.0;
    }

    private void subtractCost(Item item){
        double defaultCostOfUse = 0.5;
        double cost = item.getCostFromStonesOfColor(MagicColor.BLUE);
        item.setBaseStrength(item.getBaseStrength() - defaultCostOfUse - cost);
    }

}
