package item;

public class AttackVisitor implements ItemVisitor {

    @Override
    public double visit(Weapon item) {
        return useAttack(item);
    }

    protected double useAttack(Item item) {
        double defaultCostOfUse = 0.5;
        double baseStrength = item.getBaseStrength();
        double strengthFromStones = item.getStrengthFromStonesOfColor(MagicColor.BLUE);
        double cost = item.getCostFromStonesOfColor(MagicColor.BLUE);
        double stoneStrengthFactor = 1 + (strengthFromStones/100);

        item.setBaseStrength(item.getBaseStrength() - defaultCostOfUse - cost);

        return Math.round(100 * baseStrength * stoneStrengthFactor) / 100.0;
    }


}
