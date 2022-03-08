package model;

// Represents an item with a name, price, and itemType
public enum MenuItem {
    //Base flavours
    vanilla("Vanilla Yogurt", 2.00, 1, "white"),
    chocolate("Chocolate Yogurt", 2.00, 1, "brown"),
    strawberry("Strawberry Yogurt", 2.00, 1, "lightRed"),
    lime("Zesty Lime Yogurt", 2.50, 1, "green"),
    peanutButter("Peanut Butter  Yogurt", 2.50, 1, "lightBrown"),
    wildBerry("Wild Berry Jumble Yogurt", 2.50, 1, "purple"),
    mangoSorbet("Mango Sorbet", 2.75, 1, "yellow"),
    orangeVanilla("Orange Vanilla Sorbet", 2.75, 1, "orange"),
    cherry("Cherry Sorbet", 2.75, 1, "darkRed"),

    //Toppings:
    //Fresh Fruit
    blueberries("Blueberries", 1.10, 2, "blue"),
    mango("Mango", 1.30, 2, "yellow"),
    strawberries("Strawberries", 1.50, 2, "lightRed"),
    raspberries("Raspberries", 1.90, 2, "darkRed"),
    pineapple("Pineapple", 1.25, 2, "yellow"),
    banana("Banana", 0.95, 2, "yellow"),

    // Yummy Toppings
    sprinkles("Sprinkles", 0.10, 3, "rainbow"),
    coconut("Coconut Shavings", 0.30, 3, "white"),
    darkChocolateChips("Dark Chocolate Chips", 0.40, 3, "darkBrown"),
    whiteChocolateChips("White Chocolate Chips", 0.40, 3, "white"),
    kitKat("Kit Kat Pieces", 0.50, 3, "darkBrown"),
    oreo("Oreo Pieces", 0.70, 3, "darkBrown"),
    gummyBears("Gummy Bears", 0.70, 3, "rainbow"),
    cheesecake("Cheesecake Pieces", 1.20, 3, "white"),
    mochi("Mochi Pieces", 1.15, 3, "rainbow"),

    // Syrups and Sauces
    caramel("Caramel Sauce", 1.30, 4, "brown"),
    condensedMilk("Condensed Milk", 0.85, 4, "white"),
    butterscotch("Butterscotch", 0.90, 4, "brown"),
    chocolateFudge("Chocolate Fudge", 1.45, 4, "darkBrown");


    private String name;
    private double price;
    private int itemType;
    private String color;

    /*
     * Order types:
     * 1. Base Flavour
     * 2. Fresh fruit topping
     * 3. Yummy topping
     * 4. syrup and sauce
     */
    /*
     * EFFECTS: Constructs an item with a name, price, and itemType
     */
    MenuItem(String name, double price, int itemType, String color) {
        this.name = name;
        this.price = price;
        this.itemType = itemType;
        this.color = color;
    }


    /*
     * GETTERS
     */
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getItemType() {
        return itemType;
    }

    public String getColor() {
        return color;
    }


}
