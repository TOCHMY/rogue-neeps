import java.util.ArrayList;
import java.util.List;

public class VisitorTester {

    public static void main(String[] args) {
        AttackVisitor attackVisitor = new AttackVisitor();
        DefenceVisitor defenceVisitor = new DefenceVisitor();

        List<Item> items = new ArrayList<>();

        items.add(new Weapon(20, List.of(new MagicSocket(MagicColor.BLUE), new MagicSocket(MagicColor.RED))));
        items.add(new Armor(20, List.of( new MagicSocket(MagicColor.RED))));
        items.get(1).addStone(new GemStone(MagicColor.RED, 8, 2));

        double attackStrength = items.stream()
                .mapToDouble(attackVisitor::useAttack)
                .sum();

        double defenceStrength = items.stream()
                .mapToDouble(defenceVisitor::useDefence)
                .sum();

        System.out.println(defenceStrength);


    }
}
