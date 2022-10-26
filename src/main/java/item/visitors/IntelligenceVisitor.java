package item.visitors;

import item.items.Armor;
import item.items.Item;
import item.stonesystem.MagicColor;

public class IntelligenceVisitor implements ItemVisitor {

    @Override
    public double visit(Armor item) {
        double intelligenceStrength = useIntelligence(item);
        subtractCostFromStones(item);

        return intelligenceStrength;
    }

    private void subtractCostFromStones(Item item) {
        item.setBaseStrength(item.getBaseStrength() - item.getCostFromStonesOfColor(MagicColor.GREEN));
    }

    private double useIntelligence(Item item) {
        double baseStrength = item.getBaseStrength();
        double strengthFromStones = item.getStrengthFromStonesOfColor(MagicColor.GREEN);

        return baseStrength * strengthFromStones / 100;
    }

}
