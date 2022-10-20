import java.util.*;

public class ItemCollection {

    private Item leftHandItem;
    private Item rightHandItem;
    private Armor armor;
    private MagicBag magicBag;

    public ItemCollection() {
    }

    public List<Item> getHandItems() {
        List<Item> items = new ArrayList<>();
        if (leftHandItem != null)
            items.add(leftHandItem);
        if (rightHandItem != null)
            items.add(rightHandItem);

        return items;
    }

    public void addArmor(Armor armor) {
        if (this.armor != null)
            throw new IllegalStateException("There is already an armor");
        this.armor = armor;
    }

    public Armor getArmor() {
        return armor;
    }

    public Optional<Armor> removeArmor() {
        Armor armorToRemove = this.armor;
        this.armor = null;
        return Optional.of(armorToRemove);
    }

    public void addRightHandItem(Item item) {
        if (this.rightHandItem != null)
            throw new IllegalStateException("There is already a right hand item");
        if (countNumberOfShields() >= 1)
            throw new IllegalStateException("There can only be one shield");
        if (!(item instanceof Weapon) && !(item instanceof Shield))
            throw new IllegalArgumentException("Only weapons and shield can be handheld");

        this.rightHandItem = item;
    }

    public void addLeftHandItem(Item item) {
        if (this.leftHandItem != null)
            throw new IllegalStateException("There is already a left hand item");
        if (countNumberOfShields() >= 1)
            throw new IllegalStateException("There can only be one shield");
        if (!(item instanceof Weapon) && !(item instanceof Shield))
            throw new IllegalArgumentException("Only weapons and shield can be handheld");

        this.leftHandItem = item;
    }

    private long countNumberOfShields() {
        return getAllItems().stream()
                .filter(item -> item instanceof Shield)
                .count();
    }

    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        if (leftHandItem != null)
            allItems.add(leftHandItem);
        if (rightHandItem != null)
            allItems.add(rightHandItem);
        if (armor != null)
            allItems.add(armor);
        if (magicBag != null)
            allItems.add(magicBag);

        return Collections.unmodifiableList(allItems);
    }

    public Optional<Item> removeRightHandItem() {
        Item itemToRemove = this.rightHandItem;
        rightHandItem = null;
        return Optional.of(itemToRemove);
    }

    public Optional<Item> removeLeftHandItem() {
        Item itemToRemove = this.leftHandItem;
        leftHandItem = null;
        return Optional.of(itemToRemove);
    }

    public Item getLeftHandItem() {
        return leftHandItem;
    }

    public Item getRightHandItem() {
        return rightHandItem;
    }
}