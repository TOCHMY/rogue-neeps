package item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCollectionTest {

    private ItemCollection ITEM_COLLECTION;
    private Weapon WEAPON;
    private Armor ARMOR;
    private Shield SHIELD;
    private List<MagicSocket> DEFENCE_SOCKETS;
    private List<MagicSocket> ATTACK_DEFENCE_SOCKETS;

    @BeforeEach
    void setup() {
        ITEM_COLLECTION = new ItemCollection();
        DEFENCE_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        ATTACK_DEFENCE_SOCKETS = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED));
        ARMOR = new Armor(50, DEFENCE_SOCKETS);
        WEAPON = new Weapon(50, ATTACK_DEFENCE_SOCKETS);
        SHIELD = new Shield(50, DEFENCE_SOCKETS);
    }

    @Test
    void testInitializeItemCollection() {
        ItemCollection items = new ItemCollection();
        assertNotNull(items);
    }

    @Test
    void testAddArmor() {
        Armor armor = new Armor(50, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);

        assertEquals(armor, ITEM_COLLECTION.getArmor());
    }

    @Test
    void testAddTooManyArmors() {
        Armor armor = new Armor(50, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);
        assertThrows(IllegalStateException.class, () -> ITEM_COLLECTION.addArmor(armor));
    }

    @Test
    void testRemoveArmor() {
        Armor armor = new Armor(50, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);
        assertEquals(armor, ITEM_COLLECTION.removeArmor().get());
        assertNull(ITEM_COLLECTION.getArmor());
    }

    @Test
    void testHandsAreCorrectlyEmpty() {
        assertTrue(ITEM_COLLECTION.getHandItems().isEmpty());
    }

    @Test
    void testAddWeaponToEachHand() {
        Weapon weapon1 = new Weapon(50, ATTACK_DEFENCE_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(weapon1);
        assertTrue(ITEM_COLLECTION.getHandItems().contains(weapon1));
        Weapon weapon2 = new Weapon(60, ATTACK_DEFENCE_SOCKETS);
        ITEM_COLLECTION.addLeftHandItem(weapon2);
        assertTrue(ITEM_COLLECTION.getHandItems().contains(weapon1));
        assertTrue(ITEM_COLLECTION.getHandItems().contains(weapon2));
    }

    @Test
    void testAddTooManyWeapons() {
        Weapon weapon1 = new Weapon(50, ATTACK_DEFENCE_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(weapon1);
        Weapon weapon2 = new Weapon(60, ATTACK_DEFENCE_SOCKETS);
        ITEM_COLLECTION.addLeftHandItem(weapon2);

        assertThrows(IllegalStateException.class, () -> ITEM_COLLECTION.addLeftHandItem(new Weapon(80, ATTACK_DEFENCE_SOCKETS)));
        assertThrows(IllegalStateException.class, () ->
            ITEM_COLLECTION.addRightHandItem(new Weapon(77.5, ATTACK_DEFENCE_SOCKETS))
        );
    }

    @Test
    void testRemoveRightHandItem() {
        Weapon weapon = new Weapon(50, ATTACK_DEFENCE_SOCKETS);

        ITEM_COLLECTION.addRightHandItem(weapon);

        assertEquals(weapon, ITEM_COLLECTION.removeRightHandItem().get());
        assertNotEquals(ITEM_COLLECTION.getRightHandItem(), weapon);
    }

    @Test
    void testRemoveLeftHandItem() {
        Weapon weapon = new Weapon(60, ATTACK_DEFENCE_SOCKETS);

        ITEM_COLLECTION.addLeftHandItem(weapon);

        assertEquals(weapon, ITEM_COLLECTION.removeLeftHandItem().get());
        assertNotEquals(ITEM_COLLECTION.getLeftHandItem(), weapon);
    }

    @Test
    void testAddShieldToLeftHand() {
        Shield shield = new Shield(32, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(shield);
        assertTrue(ITEM_COLLECTION.getHandItems().contains(shield));
    }

    @Test
    void testAddShieldToHand() {
        Shield shield = new Shield(32, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(shield);
        assertTrue(ITEM_COLLECTION.getHandItems().contains(shield));
        assertEquals(shield, ITEM_COLLECTION.getRightHandItem());
        ITEM_COLLECTION.removeRightHandItem();
        ITEM_COLLECTION.addLeftHandItem(shield);
        assertTrue(ITEM_COLLECTION.getHandItems().contains(shield));
        assertEquals(shield, ITEM_COLLECTION.getLeftHandItem());
    }

    @Test
    void testMoreThanOneShield() {
        Shield shield = new Shield(32, DEFENCE_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(shield);
        assertThrows(IllegalStateException.class, () -> {
            ITEM_COLLECTION.addLeftHandItem(shield);
        });
        ITEM_COLLECTION.removeRightHandItem();
        ITEM_COLLECTION.addLeftHandItem(shield);
        assertThrows(IllegalStateException.class, () -> {
            ITEM_COLLECTION.addRightHandItem(shield);
        });
    }

    @Test
    void testAddWrongTypeToHand() {
        assertThrows(IllegalArgumentException.class, () ->{
            ITEM_COLLECTION.addLeftHandItem(ARMOR);
        });
        assertThrows(IllegalArgumentException.class, () ->{
            ITEM_COLLECTION.addRightHandItem(ARMOR);
        });
    }

    @Test
    void testGetAllItems() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        assertTrue(ITEM_COLLECTION.getAllItems().contains(WEAPON));
        ITEM_COLLECTION.addLeftHandItem(SHIELD);
        assertTrue(ITEM_COLLECTION.getAllItems().containsAll(List.of(WEAPON,SHIELD)));
        ITEM_COLLECTION.addArmor(ARMOR);
        assertTrue(ITEM_COLLECTION.getAllItems().containsAll(List.of(WEAPON,ARMOR,SHIELD)));
        ITEM_COLLECTION.removeArmor();
        assertTrue(ITEM_COLLECTION.getAllItems().containsAll(List.of(WEAPON,SHIELD)));
        ITEM_COLLECTION.removeLeftHandItem();
        assertTrue(ITEM_COLLECTION.getAllItems().contains(WEAPON));
        ITEM_COLLECTION.removeRightHandItem();
        assertTrue(ITEM_COLLECTION.getAllItems().isEmpty());
    }

    @Test
    void testUseAllItemsInAttack() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);
        ITEM_COLLECTION.addArmor(ARMOR);

        double expectedStrength = 50;
        assertEquals(expectedStrength, ITEM_COLLECTION.attackWithItems());
    }

    @Test
    void testUseAllItemsInDefence() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);
        ITEM_COLLECTION.addArmor(ARMOR);

        double expectedStrength = 125;

        assertEquals(expectedStrength, ITEM_COLLECTION.defendWithItems());
    }

    @Test
    void testArmorWeakerThan15HalvesAttack() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);

        ITEM_COLLECTION.addArmor(new Armor(10, DEFENCE_SOCKETS));

        double expectedStrength = 25;
        assertEquals(expectedStrength, ITEM_COLLECTION.attackWithItems());
    }

}
