//package ui;
//
//import model.*;
//import model.MenuItem;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//
//import javax.swing.*;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.*;
//
//// Represents the clerk application
//public class ClerkApp extends JPanel {
//
//    private static final int REWARD_MULTIPLIER = 5;
//    private static final double TOTAL_TAX = 0.12;
//
//    private boolean workingOrder = false;
//    private boolean runningApp = true;
//
//    private final MenuTypes menu = new MenuTypes();
//
//    private int checkMemberID;
//
//    private Order myOrder;
//    private ListItem orderList;
//    private Member member;
//    private ListItem memberList;
//
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//    private static final String JSON_STORE = "./data/masterlist.json";
//
//    /*
//     * EFFECTS: Constructs a ClerkApp
//     */
//    public ClerkApp() {
//        LaunchPage launchPage = new LaunchPage();
//
//        orderList = new ListItem();
//        memberList = new ListItem();
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runClerkApp();
//
//
//    }
//
////    public void createGUI() {
////        JFrame frame = new JFrame("Frozen Yogurt");
////
////
////        frame.pack();
////        frame.setVisible(true);
////    }
//
//    /*
//     * EFFECTS: Starts the application.
//     */
//    private void runClerkApp() {
//        System.out.println("Welcome, what would you like to do today?");
//        while (runningApp) {
//            displayMenu();
//            getCommand();
//        }
//    }
//
//    /*
//     * EFFECTS: Displays a menu of user options.
//     */
//    private void displayMenu() {
//        printLines();
//        System.out.println("Please type in a command:");
//        printLines();
//        if (!workingOrder) {
//            System.out.println("Order : Place an order");
//        } else {
//            System.out.println("Add : Add an item to current order");
//            System.out.println("Remove : Remove an item from current order");
//            System.out.println("View : View current order");
//            System.out.println("Pay : Request to pay");
//        }
//        System.out.println("Membership : Check membership");
//        System.out.println("Save : Terminate program and save master lists");
//        System.out.println("Load : Loads member lists"); //TODO: Incorporate loading screen to load separately
//    }
//
//    /*
//     * EFFECTS: Takes in user input for order commands
//     */
//    private void getCommand() {
//        Scanner scanner = new Scanner(System.in);
//        String command = scanner.next();
//        if (command.equals("Membership")) {
//            checkMembership();
//        } else if (command.equals("Save")) {
//            saveData();
//            runningApp = false;
//        } else if (command.equals("Load")) {
//            loadData();
//        } else if (!workingOrder) {
//            noWorkingOrderCommands(command);
//        } else {
//            hasWorkingOrderCommands(command);
//        }
//    }
//
//    /*
//     * EFFECTS: Displays the corresponding commands available for when there is no existing order
//     */
//    private void noWorkingOrderCommands(String command) {
//        if ("Order".equals(command)) {
//            placeOrder();
//        } else {
//            System.out.println("Invalid entry, please try again");
//        }
//    }
//
//    /*
//     * EFFECTS: Displays the corresponding commands available for when there is an existing order.
//     */
//    private void hasWorkingOrderCommands(String command) {
//        switch (command) {
//            case ("Add"):
//                chooseItemType();
//                break;
//            case ("Remove"):
//                removeItems();
//                break;
//            case ("View"):
//                viewOrder();
//                break;
//            case ("Pay"):
//                addToDatabase();
//                printReceipt();
//                break;
//            default:
//                System.out.println("Invalid entry, please try again");
//        }
//    }
//
//    /*
//     * MODIFIES: this
//     * EFFECTS: Creates a new order for current customer. If membership exists then grabs member info, otherwise
//     *          creates a new member, sets the orderNumber and starts item additions.
//     */
//    private void placeOrder() {
//        workingOrder = true;
//        System.out.println("Please enter your member ID.  Enter 0 if you do not have a membership");
//        if (!isMember()) {
//            System.out.println("Did not recognize Membership ID. Creating new membership.");
//            newMember();
//        } else {
//            member = (Member) memberList.getItem(checkMemberID);
//            System.out.println("Welcome back " + member.getMemberName());
//        }
//        myOrder = new Order();
//        myOrder.setOrderNumber(orderList.length() + 1);
//        chooseItemType();
//    }
//
//    /*
//     * EFFECTS: Cycles through menu and displays each possible Item Type (such as Base Flavour, Fresh Fruit, etc...)
//     *          Takes user input and called method to display and add specified Item Type to Order
//     */
//    public void chooseItemType() {
//        while (true) {
//            int optionsCount = 1;
//            System.out.println("Enter a number to choose a type: ");
//            Set<Integer> keys = menu.getMenuTypes().keySet(); //Create a set of the keys in menuType
//            for (Integer i : keys) { //Prints the number and names of the different types of items
//                System.out.println(i + ":" + menu.getMenuTypes().get(i));
//                optionsCount++;
//            }
//            System.out.println("0 : To finish adding items to an order");
//
//            int userChoice = validateIntScannerInput();
//            if (userChoice == 0) { //Finish adding items to order
//                break;
//            } else if (userChoice >= 1 && userChoice < optionsCount) { //Limits user input to only displayed
//                chooseItemsToAdd(userChoice);
//            } else {
//                System.out.println("Invalid entry, please try again");
//            }
//        }
//    }
//
//    /*
//     * REQUIRES: valid itemType number within the Item enumeration.  User input to be integer
//     * MODIFIES: this
//     * EFFECTS: Cycle through Item and prints each Item of specified Item Type.
//     *          Gets user input for corresponding Item and to add to order.
//     */
//    public void chooseItemsToAdd(int itemType) {
//        while (true) {
//            int itemizedCount = 1;
//            System.out.println("Please enter a number to add a "
//            + menu.getMenuTypes().get(itemType) + " to an order");
//            printLines();
//            for (MenuItem i : MenuItem.values()) { //Prints the number and names of the Items of itemType
//                if (i.getItemType() == itemType) {
//                    System.out.println(itemizedCount + ": " + i.getName());
//                    itemizedCount++;
//                }
//            }
//            System.out.println("0 : To finish adding " + menu.getMenuTypes().get(itemType) + " to an order");
//            int choice = validateIntScannerInput();
//            if (choice == 0) {
//                break;
//            } else if (choice >= 1 && choice < itemizedCount) { //Limit user choice
//                // Adds Item from Item enum.
//                Gets an index int of first occurrence of that itemType adds user choice int
//                //  to the enumeration index.
//                myOrder.addItem(MenuItem.values()[choice + menu.getItemLengthBeforeFirstOccurrence(itemType) - 1]);
//            } else {
//                System.out.println("Invalid, please try again");
//            }
//        }
//    }
//
//    /*
//     * MODIFIES: this
//     * EFFECTS: Displays list of current items and gets user input and removes specified Item from current Order
//     */
//    public void removeItems() {
//        while (true) {
//            viewOrder();
//            System.out.println("0 : To finish removing items");
//            System.out.println("Please type in the item number you'd like to remove: ");
//            int itemNumber = validateIntScannerInput();
//            if (itemNumber >= 1 && itemNumber <= myOrder.getOrderSize()) { // Limits user input
//                myOrder.removeItem(itemNumber);
//            } else if (itemNumber == 0) {
//                break;
//            } else {
//                System.out.println("Invalid, please try again");
//                printLines();
//            }
//        }
//    }
//
//    /*
//     * MODIFIES: this
//     * EFFECTS: Adds reward points to member data,
//     *          adds order to master list of orders,
//     *          adds member to master list of members,
//     *          sets current customer to the order,
//     *          add the price of this order to the total spent value of the member
//     */
//    private void addToDatabase() {
//        int intSubTotal = (int) myOrder.getSubTotal();
//        member.addPoints(intSubTotal * REWARD_MULTIPLIER);
//        orderList.addOrderToOrderList(myOrder);
//        memberList.addMemberToMemberList(member);
//        myOrder.setMemberID(member.getMemberID());
//        member.addTotalSpent(myOrder.getSubTotal());
//    }
//
//    /*
//     * EFFECTS: Displays membership info of current member,
//     *          and prints out an itemized list of Items in the Order
//     *          and prints the price subtotal, tab, and total,
//     *          completes the order and changes app stat of workingOrder to false.
//     */
//    private void printReceipt() {
//        displayMembershipInfo(member.getMemberID());
//        System.out.println("Order Number: " + myOrder.getOrderNumber());
//        viewOrder();
//        System.out.println("Subtotal Comes: $" + String.format("%.2f", myOrder.getSubTotal()));
//        System.out.println("TAX: " + String.format("%.2f", TOTAL_TAX * 100) + "%");
//        System.out.println("Order Total: $" + String.format("%.2f", myOrder.getSubTotal() * (1 + TOTAL_TAX)));
//        workingOrder = false;
//    }
//
//    /*
//     * EFFECTS: Prints out an itemized list of Items in the Order.
//     */
//    private void viewOrder() {
//        int count = 1;
//        System.out.println("Your current order is:");
//        printLines();
//        for (MenuItem i : myOrder.getList()) {
//            System.out.println(count + ". " + i.getName());
//            count++;
//        }
//        printLines();
//    }
//
//    /*
//     * MODIFIES: this
//     * EFFECTS: Asks user for String and creates a new member
//     */
//    public void newMember() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("What is your name?");
//        String name = scanner.next();
//        member = new Member(name, memberList.length() + 1);
//    }
//
//    /*
//     * EFFECTS: Checks if member exists and if so displays membership info
//     */
//    public void checkMembership() {
//        System.out.println("Please enter the membership ID you'd like to check");
//        if (isMember()) {
//            displayMembershipInfo(checkMemberID);
//        } else {
//            System.out.println("Sorry, cannot find that member");
//        }
//    }
//
//    /*
//     * MODIFIES: this
//     * EFFECTS: checks if user input is a member and returns true if it is otherwise return false
//     */
//    private boolean isMember() {
//        checkMemberID = validateIntScannerInput();
//        return memberList.containsItem(checkMemberID);
//    }
//
//    /*
//     * EFFECTS: Displays the member's info
//     */
//    public void displayMembershipInfo(int memberID) {
//        System.out.println("Name: " + memberList.getItem(memberID).getMemberName()
//                + "\nMember ID: " + memberList.getItem(memberID).getMemberID()
//                + "\nReward Point Balance: " + memberList.getItem(memberID).getRewardPoints()
//                + "\nTotal Spent: " + String.format("%.2f", memberList.getItem(memberID).getTotalSpent()));
//        printLines();
//    }
//
//    /*
//     * EFFECTS: Print line breaks
//     */
//    public void printLines() {
//        System.out.println("-------------------");
//    }
//
//    /*
//     * This method references code from Wayan at KodeJava
//     * Link: "https://kodejava.org/how-do-i-validate-input-when-using-scanner/#:~:text=To%20validate
//     *                                 %20input%20the%20Scanner,provide%20a%20positive%20integer%20number."
//     *
//     * EFFECTS: Continuously scans user input and checks if it is an integer >0.  Returns valid user int input.
//     */
//    public int validateIntScannerInput() {
//        Scanner scanner = new Scanner(System.in);
//        int number;
//        do {
//            System.out.print("Please enter a number: ");
//            while (!scanner.hasNextInt()) {
//                String input = scanner.next();
//                System.out.printf("\"%s\" is not a valid number.\n", input);
//            }
//            number = scanner.nextInt();
//        } while (number < 0);
//        return number;
//    }
//
//    /*
//     * EFFECTS: saves memberList and orderList to file
//     */
//    private void saveData() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(memberList, orderList);
//            jsonWriter.close();
//            System.out.println("Saved stuff list of members and list of orders");
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file.  File not found.");
//        }
//    }
//
//    /*
//     * MODIFIES: this
//     * EFFECTS: loads memberList and orderList from file
//     */
//    private void loadData() {
//        try {
//            memberList = jsonReader.read("memberList");
//            orderList = jsonReader.read("orderList");
//        } catch (IOException e) {
//            System.out.println("Could not read the file. Please try again");
//        }
//    }
//
//
//
//}
