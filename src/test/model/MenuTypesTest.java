package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTypesTest {

    private MenuTypes testMenuType;

    @BeforeEach
    public void runBefore() {
        testMenuType = new MenuTypes();
    }

    @Test
    public void testGetMenuTypes() {
        LinkedHashMap<Integer, String> testFilledMenuType = new LinkedHashMap<>();
        testFilledMenuType.put(1, "Base Flavour");
        testFilledMenuType.put(2, "Fresh Fruit");
        testFilledMenuType.put(3, "Yummy Toppings");
        testFilledMenuType.put(4, "Syrup or Fudge");
        assertEquals(testMenuType.getMenuTypes(), testFilledMenuType) ;
    }

    @Test
    public void testGetTypeLength() {
        assertEquals(testMenuType.getItemLengthBeforeFirstOccurrence(1), 0);
        assertEquals(testMenuType.getItemLengthBeforeFirstOccurrence(2), 9);
        assertEquals(testMenuType.getItemLengthBeforeFirstOccurrence(3), 15);
        assertEquals(testMenuType.getItemLengthBeforeFirstOccurrence(4), 24);
        assertEquals(testMenuType.getItemLengthBeforeFirstOccurrence(5), 28);
    }



}
