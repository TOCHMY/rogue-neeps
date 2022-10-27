package item;

import item.items.Armor;
import item.items.Shield;
import item.items.Weapon;
import item.stonesystem.GemStone;
import item.stonesystem.MagicColor;
import item.stonesystem.MagicSocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCollectionTest {

    private ItemCollection ITEM_COLLECTION;
    private Weapon WEAPON;
    private Armor ARMOR;
    private Shield SHIELD;
    private List<MagicSocket> SHIELD_SOCKETS;
    private List<MagicSocket> ARMOR_SOCKETS;
    private List<MagicSocket> ATTACK_DEFENCE_SOCKETS;

    @BeforeEach
    void setup() {
        ITEM_COLLECTION = new ItemCollection();
        SHIELD_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.RED));
        ARMOR_SOCKETS = List.of
                (new MagicSocket(MagicColor.RED),
                        new MagicSocket(MagicColor.GREEN),
                        new MagicSocket(MagicColor.RED));
        ATTACK_DEFENCE_SOCKETS = List.of
                (new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.BLUE),
                        new MagicSocket(MagicColor.RED));
        ARMOR = new Armor(50, ARMOR_SOCKETS);
        WEAPON = new Weapon(50, ATTACK_DEFENCE_SOCKETS);
        SHIELD = new Shield(50, SHIELD_SOCKETS);
    }

    @Test
    void testInitializeItemCollection() {
        ItemCollection items = new ItemCollection();

        assertNotNull(items);
    }

    @Test
    void testAddArmor() {
        Armor armor = new Armor(50, ARMOR_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);

        assertEquals(armor, ITEM_COLLECTION.getArmor());
    }

    @Test
    void testAddTooManyArmors() {
        Armor armor = new Armor(50, ARMOR_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);

        assertThrows(IllegalStateException.class, () -> ITEM_COLLECTION.addArmor(armor));
    }

    @Test
    void testRemoveArmor() {
        Armor armor = new Armor(50, ARMOR_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);

        ITEM_COLLECTION.removeArmor();

        assertNull(ITEM_COLLECTION.getArmor());
    }

    @Test
    void testRemoveArmorReturnsArmor() {
        Armor armor = new Armor(50, ARMOR_SOCKETS);
        ITEM_COLLECTION.addArmor(armor);

        assertEquals(armor, ITEM_COLLECTION.removeArmor().orElseThrow(NullPointerException::new));
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

        assertTrue(ITEM_COLLECTION.getHandItems().containsAll(List.of(weapon1, weapon2)));
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
        ITEM_COLLECTION.removeRightHandItem();

        assertNull(ITEM_COLLECTION.getRightHandItem());
    }

    @Test
    void testRemoveLeftHandItem() {
        Weapon weapon = new Weapon(60, ATTACK_DEFENCE_SOCKETS);

        ITEM_COLLECTION.addLeftHandItem(weapon);
        ITEM_COLLECTION.removeLeftHandItem();

        assertNull(ITEM_COLLECTION.getLeftHandItem());
    }

    @Test
    void testRemoveRightHandItemReturnsItem() {
        Weapon weapon = new Weapon(50, ATTACK_DEFENCE_SOCKETS);

        ITEM_COLLECTION.addRightHandItem(weapon);

        assertEquals(weapon, ITEM_COLLECTION.removeRightHandItem().orElseThrow(NullPointerException::new));
    }

    @Test
    void testRemoveLeftHandItemReturnsItem() {
        Weapon weapon = new Weapon(60, ATTACK_DEFENCE_SOCKETS);

        ITEM_COLLECTION.addLeftHandItem(weapon);

        assertEquals(weapon, ITEM_COLLECTION.removeLeftHandItem().orElseThrow(NullPointerException::new));
    }

    @Test
    void testGetHandItemsContainsShield() {
        Shield shield = new Shield(32, SHIELD_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(shield);

        assertTrue(ITEM_COLLECTION.getHandItems().contains(shield));
    }

    @Test
    void testGetHandItemsContainsWeapon() {
        Weapon weapon = new Weapon(100, SHIELD_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(weapon);

        assertTrue(ITEM_COLLECTION.getHandItems().contains(weapon));
    }

    @Test
    void testAddShieldToRightHand() {
        Shield shield = new Shield(32, SHIELD_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(shield);

        assertEquals(shield, ITEM_COLLECTION.getRightHandItem());
    }

    @Test
    void testAddShieldToLeftHand() {
        Shield shield = new Shield(32, SHIELD_SOCKETS);
        ITEM_COLLECTION.addLeftHandItem(shield);

        assertEquals(shield, ITEM_COLLECTION.getLeftHandItem());
    }

    @Test
    void testMoreThanOneShieldRightHand() {
        Shield shield = new Shield(32, SHIELD_SOCKETS);
        ITEM_COLLECTION.addLeftHandItem(shield);

        assertThrows(IllegalStateException.class, () -> {
            ITEM_COLLECTION.addRightHandItem(shield);
        });
    }
    @Test
    void testMoreThanOneShieldLeftHand() {
        Shield shield = new Shield(32, SHIELD_SOCKETS);
        ITEM_COLLECTION.addRightHandItem(shield);

        assertThrows(IllegalStateException.class, () -> {
            ITEM_COLLECTION.addLeftHandItem(shield);
        });

    }

    @Test
    void testAddWrongTypeToHand() {
        assertThrows(IllegalArgumentException.class, () -> {
            ITEM_COLLECTION.addLeftHandItem(ARMOR);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ITEM_COLLECTION.addRightHandItem(ARMOR);
        });
    }


    @Test
    void testGetAllItemsOneItem() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        assertTrue(ITEM_COLLECTION.getAllItems().contains(WEAPON));
    }


    @Test
    void testGetAllItemsTwoItems() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);

        assertTrue(ITEM_COLLECTION.getAllItems().containsAll(List.of(WEAPON, SHIELD)));
    }

    @Test
    void testGetAllItemsThreeItems() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);
        ITEM_COLLECTION.addArmor(ARMOR);

        assertTrue(ITEM_COLLECTION.getAllItems().containsAll(List.of(WEAPON, ARMOR, SHIELD)));
    }

    @Test
    void testRemoveAll() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);
        ITEM_COLLECTION.addArmor(ARMOR);
        ITEM_COLLECTION.removeArmor();
        ITEM_COLLECTION.removeLeftHandItem();
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
    void testUseAllItemsWithStonesInDefence() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);
        ITEM_COLLECTION.getLeftHandItem().addStone(new GemStone(MagicColor.RED, 20, 2));
        ITEM_COLLECTION.addArmor(ARMOR);

        double expectedStrength = 135;

        assertEquals(expectedStrength, ITEM_COLLECTION.defendWithItems());
    }

    @Test
    void testArmorWeakerThan15HalvesAttack() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);
        ITEM_COLLECTION.addArmor(new Armor(10, ARMOR_SOCKETS));

        double expectedStrength = 25;
        assertEquals(expectedStrength, ITEM_COLLECTION.attackWithItems());
    }

    @Test
    void testNoArmorUnaffectedAttack() {
        ITEM_COLLECTION.addRightHandItem(WEAPON);
        ITEM_COLLECTION.addLeftHandItem(SHIELD);

        double expectedStrength = 50;
        assertEquals(expectedStrength, ITEM_COLLECTION.attackWithItems());
    }

    @Test
    void testUseIntelligenceWithoutStones() {
        ITEM_COLLECTION.addArmor(ARMOR);
        assertEquals(0, ITEM_COLLECTION.useItemIntelligence());
    }

    @Test
    void testUseIntelligenceCostWithoutStones() {
        ITEM_COLLECTION.addArmor(ARMOR);
        ITEM_COLLECTION.useItemIntelligence();
        assertEquals(50, ITEM_COLLECTION.getArmor().getBaseStrength());
    }

    @Test
    void testUseIntelligenceWithStones() {
        ITEM_COLLECTION.addArmor(ARMOR);
        ITEM_COLLECTION.getArmor().addStone(new GemStone(MagicColor.GREEN, 13, 0.2));

        double expectedIntelligence = 50 * 13 / 100.0;
        assertEquals(expectedIntelligence, ITEM_COLLECTION.useItemIntelligence());
    }

    @Test
    void testUseIntelligenceCostWithStones() {
        ITEM_COLLECTION.addArmor(ARMOR);
        ITEM_COLLECTION.getArmor().addStone(new GemStone(MagicColor.GREEN, 13, 0.2));

        ITEM_COLLECTION.useItemIntelligence();

        double expectedRemainingStrength = 50 - 0.2;
        assertEquals(expectedRemainingStrength, ITEM_COLLECTION.getArmor().getBaseStrength());
    }



}
