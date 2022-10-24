package item;

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
        double defaultCostOfUse = 0.5;
        double baseStrength = item.getBaseStrength();
        double strengthFromStones = item.getStrengthFromStonesOfColor(MagicColor.RED);
        double cost = item.getCostFromStonesOfColor(MagicColor.RED);
        double stoneStrengthFactor = 1 + (strengthFromStones/100);

        item.setBaseStrength(item.getBaseStrength() - defaultCostOfUse - cost);

        return Math.round(100 * baseStrength * stoneStrengthFactor) / 100.0;
    }
}
