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
        double baseStrength = item.getBaseStrength();
        double strengthFromStones = item.getStrengthFromStonesOfColor(MagicColor.RED);
        double cost = item.getCostFromStonesOfColor(MagicColor.RED);

        item.setBaseStrength(item.getBaseStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100 * baseStrength * (1 + (strengthFromStones / 100))) / 100.0;
    }
}
