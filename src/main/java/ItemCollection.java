import java.util.*;

public class ItemCollection {

    private Item[] handItems;
    private Armor armor;

    public ItemCollection() {
        this.handItems = new Item[2];
    }

    public List<Item> getHandItems() {
        return Arrays.asList(handItems);
    }


    public void addArmor(Armor armor) {
        if (this.armor != null)
            throw new IllegalStateException("There is already an armor");
        this.armor = armor;
    }

    public Armor getArmor() {
        return armor;
    }

    public Armor removeArmor() {
        if (this.armor != null){
            Armor armorToRemove = this.armor;
            this.armor = null;
            return armorToRemove;
        }
        return null;
    }
}
