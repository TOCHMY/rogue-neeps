public class DefenceVisitor implements ItemVisitor {

    public double visit(Item item) {
        if (item instanceof Armor)
            return useDefence(item);
        else if (item instanceof Weapon) {
            return 0.5 * useDefence(item);
        } else return 0;
    }


    protected double useDefence(Item item) {
        double DEFAULT_COST_OF_USE = 0.5;
        double power = item.getStrength();
        double defencePowerFromStones = item.getStrengthFromStonesOfColor(MagicColor.RED);
        double cost = item.getCostFromStonesOfColor(MagicColor.RED);

        item.setStrength(item.getStrength() - DEFAULT_COST_OF_USE - cost);

        return Math.round(100 * power * (1 + (defencePowerFromStones / 100))) / 100.0;
    }
}
