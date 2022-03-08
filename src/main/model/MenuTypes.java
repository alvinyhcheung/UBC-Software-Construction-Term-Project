package model;

import java.util.LinkedHashMap;

// Represents MenuTypes with an itemType and name
public class MenuTypes {

    private final LinkedHashMap<Integer, String> menuType = new LinkedHashMap<>();

    /*
     * EFFECTS: constructs and adds the different menu types to the Map of menuTypes
     */
    public MenuTypes() {
        menuType.put(1, "Base Flavour");
        menuType.put(2, "Fresh Fruit");
        menuType.put(3, "Yummy Toppings");
        menuType.put(4, "Syrup or Fudge");
    }

    /*
     * GETTERS
     */
    public LinkedHashMap<Integer, String> getMenuTypes() {
        return menuType;
    }

    /*
     * REQUIRES: valid itemType number within the Item enumeration
     * EFFECTS: gets the size of Item enumeration class right before the start of the itemType of interest
     */
    public int getItemLengthBeforeFirstOccurrence(int itemType) {
        int length = 0;
        for (MenuItem i : MenuItem.values()) {
            if (i.getItemType() == itemType) {
                break;
            }
            length++;
        }
        return length;
    }
}
