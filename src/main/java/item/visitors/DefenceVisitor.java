package item.visitors;

import item.items.Armor;
import item.items.Item;
import item.items.Shield;
import item.items.Weapon;
import item.stonesystem.MagicColor;

public class DefenceVisitor implements ItemVisitor {

    @Override
    public double visit(Weapon item) {
        return 0.5 * useDefence(item);
    }

    @Override
    public double visit(Armor item) {
        return useDefence(item);
    }

    @Override
    public double visit(Shield item) {
        return useDefence(item);
    }

    private double useDefence(Item item) {
        double defenceStrength = getDefenceStrength(item);
        subtractCost(item);
        return defenceStrength;
    }

    private void subtractCost(Item item){
        double defaultCostOfUse = 0.5;
        double cost = item.getCostFromStonesOfColor(MagicColor.RED);

        item.setBaseStrength(item.getBaseStrength() - defaultCostOfUse - cost);
    }

    private double getDefenceStrength(Item item) {
        double baseStrength = item.getBaseStrength();
        double strengthFromStones = item.getStrengthFromStonesOfColor(MagicColor.RED);
        double stoneStrengthFactor = 1 + (strengthFromStones / 100);

        return Math.round(100 * baseStrength * stoneStrengthFactor) / 100.0;
    }
}
