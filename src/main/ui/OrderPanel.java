package ui;

import ui.exceptions.CancelledOrderException;
import model.*;
import model.MenuItem;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.swing.JOptionPane.showMessageDialog;
import static ui.LaunchPage.*;

// Represents the ordering panel GUI
public class OrderPanel extends JFrame implements ActionListener {

    private static final int FRAME_HEIGHT = 800;
    private static final int FRAME_WIDTH = 1015;

    //-------------ORDER FRAME--------------------------
    private JFrame frame = new JFrame("Frozen Yogurt - Order");
    private JPanel membershipPanel = new JPanel();

    //-------------MEMBERSHIP PANEL---------------------
    private static final int MEMBERSHIP_PANEL_HEIGHT = 50;
    private JLabel memberIDLabel = new JLabel();
    private JLabel memberNameLabel = new JLabel();
    private JLabel memberTotalSpent = new JLabel();
    private JLabel memberCurrentRewards = new JLabel();

    //-------------ITEM TYPE PANEL----------------------------
    private static final int ITEM_TYPE_PANEL_PANEL_WIDTH = 300;
    private JLabel itemTypePanel = new JLabel();
    private JButton[] itemTypeButtons = new JButton[10];
    private static final int ITEM_TYPE_BUTTON_WIDTH = 250;
    private static final int ITEM_TYPE_BUTTON_HEIGHT = 30;

    //-------------ITEM PANEL--------------------------------
    private static final int ITEM_PANEL_BUTTON_WIDTH = 200;
    private static final int ITEM_PANEL_BUTTON_HEIGHT = 80;
    private static final int ITEM_PANEL_VERTICAL_BUTTON_SPACER = 120;
    private static final int ITEM_PANEL_HORIZONTAL_BUTTON_SPACER = 50;
    private List<JLabel> itemPanelList = new ArrayList<>();
    private List<JButton[]> listOfItemButtons = new ArrayList<>();

    //-------------RECEIPT PANEL--------------------------------
    private static final int RECEIPT_PANEL_WIDTH = 400;
    private JLabel receiptPanel = new JLabel();
    private JPanel enclosure;
    private List<String> receiptList = new ArrayList<>();
    private List<JButton> receiptDeleteButtonList;
    private JScrollPane receiptScrollPane;
    private JPanel froyoPicture;

    //-------------TOTALS PANEL--------------------------------
    private static final int TOTALS_PANEL_HEIGHT = 61;
    private static final int REWARD_MULTIPLIER = 5;
    private static final double TOTAL_TAX = 0.12;
    private JPanel totalsPanel = new JPanel();
    JLabel subtotalLabel;
    JLabel taxLabel;
    JLabel orderTotalLabel;
    JButton payButton;

    private static final int MID_PANEL_HEIGHT = 650;

    private final MenuTypes menu = new MenuTypes();
    private Member member;
    private Order myOrder;
    private int checkMemberID;
    private ListItem memberList;
    private ListItem orderList;

    /*
     * ----------ORDER PANEL------------------------------------
     * EFFECTS: Instantiates
     *          Creates the orderPanel and sets the member list and order list
     */
    public OrderPanel(ListItem memberList, ListItem orderList) throws CancelledOrderException {
        this.memberList = memberList;
        this.orderList = orderList;

        setPanelBackgrounds();
        getCustomerInfo();
        createMembershipPanel();
        createItemTypePanel();
        createAllItemListPanels();
        createReceiptPanel();
        createTotalsPane();
        createOrderFrame();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets the panel backgrounds according to the color pallet
     */
    public void setPanelBackgrounds() {
        frame.setContentPane(new JLabel((new ImageIcon("./data/img/"
                + pallets[randomPalletNumber] + "/orderPageBackground.png"))));
        itemTypePanel.setIcon(new ImageIcon("./data/img/"
                + pallets[randomPalletNumber] + "/orderPageBackground.png"));
        receiptPanel.setIcon(new ImageIcon("./data/img/"
                + pallets[randomPalletNumber] + "/orderPageBackground.png"));
        membershipPanel.setBackground(new Color(palletHex[randomPalletNumber]));
        totalsPanel.setBackground(new Color(palletHex[randomPalletNumber]));
    }

    /* ----------GET CUSTOMER INFO------------------------------
     * MODIFIES: this
     * EFFECTS: If customer is not a member, call newMember() to create a new member, else, set member
     *          to member associated with the entered memberID.
     *          Sets the associated JLabels.
     */
    public void getCustomerInfo() throws CancelledOrderException {
        if (!isMember()) {
            if (checkMemberID == 0) {
                showMessageDialog(null, "Creating new membership.");
            } else {
                showMessageDialog(null,
                        "Did not recognize Membership ID. Creating new membership.");
            }
            newMember();
        } else {
            member = (Member) memberList.getItem(checkMemberID);
            showMessageDialog(null, "Welcome back " + member.getMemberName());
            memberNameLabel.setText("Member Name: " + member.getMemberName());
            memberIDLabel.setText("Member ID: " + member.getMemberID());
        }
        myOrder = new Order();
        myOrder.setOrderNumber(orderList.length() + 1);
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks if user input is a member and returns true if it is otherwise return false
     */
    public boolean isMember() throws CancelledOrderException {
        checkMemberID = validateIntScannerInput("Please enter your member ID.  \n"
                + "Enter 0 if you do not have a membership.");
        return memberList.containsItem(checkMemberID);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Asks user for String, creates a new member, and sets member labels
     */
    public void newMember() throws CancelledOrderException {
        String name;
        name = JOptionPane.showInputDialog("What is your name?");
        if (name == null) {
            showMessageDialog(null, "Order Cancelled");
            throw new CancelledOrderException();
        } else {
            while (name.equals("")) {
                name = JOptionPane.showInputDialog("Please enter a valid name.");
                if (name == null) {
                    showMessageDialog(null, "Order Cancelled");
                    throw new CancelledOrderException();
                }
            }
        }
        member = new Member(name, memberList.length() + 1);
        memberNameLabel.setText("Member Name: " + name);
        memberIDLabel.setText("Member ID: " + memberList.length() + 1);
    }

    /*
     * ----------MEMBERSHIP PANEL-------------------------------
     * MODIFIES: this
     * EFFECTS: Creates the membership panel and adds the member labels to the panel
     */
    public void createMembershipPanel() {
        membershipPanel.setBounds(0, 0, FRAME_WIDTH, MEMBERSHIP_PANEL_HEIGHT);
        membershipPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        membershipPanel.setLayout(null);

        setMemberLabels(memberNameLabel, 5, 0);
        setMemberLabels(memberIDLabel, 5, 25);
        setMemberLabels(memberTotalSpent, 210, 0);
        setMemberLabels(memberCurrentRewards, 210, 25);

        memberTotalSpent.setText("Total Spent: $" + String.format("%.2f", member.getTotalSpent()));
        memberCurrentRewards.setText("Reward Balance: " + member.getRewardPoints());

        membershipPanel.add(memberNameLabel);
        membershipPanel.add(memberIDLabel);
        membershipPanel.add(memberTotalSpent);
        membershipPanel.add(memberCurrentRewards);
    }

    /*
     * MODIFIES: label
     * EFFECTS: Sets the bounds and font for the membership labels
     */
    public void setMemberLabels(JLabel label, int x, int y) {
        label.setBounds(x, y, 500, 20);
        label.setFont(new Font(null, Font.PLAIN, 15));
    }

    /*
     * ----------ITEM TYPE PANEL--------------------------------
     * MODIFIES: this
     * EFFECTS: Create an Item Type JPanel to display item types.
     *          For each entry in the itemTypeArray, create a button with associated entry name and add to an array
     *          of buttons
     */
    public void createItemTypePanel() {
        List<String> itemTypeArray = new ArrayList<>();
        Set<Integer> keys = menu.getMenuTypes().keySet();

        for (Integer i : keys) {
            itemTypeArray.add(menu.getMenuTypes().get(i));
        }

        itemTypePanel.setBounds(0, MEMBERSHIP_PANEL_HEIGHT, ITEM_TYPE_PANEL_PANEL_WIDTH, MID_PANEL_HEIGHT);
        itemTypePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        itemTypePanel.setLayout(new FlowLayout());

        for (int i = 0; i < itemTypeArray.size(); i++) {
            itemTypeButtons[i] = new JButton(itemTypeArray.get(i));
            itemTypeButtons[i].setFocusable(false);
            itemTypeButtons[i].setPreferredSize(new Dimension(250, 30));
            itemTypeButtons[i].addActionListener(this);
            itemTypePanel.add(itemTypeButtons[i]);
        }
    }

    /*
     * ----------ITEM LIST PANEL--------------------------------
     * REQUIRES: itemType to be a valid integer within the possible enumeration types
     * MODIFIES: this
     * EFFECTS: Creates an Item List JPanel
     *          Gathers an array of MenuItems given the itemType integer identifier and for each MenuItem,
     *          a JButton is created and added to an array of JButtons.
     *          The array is then added to a list of button arrays corresponding to the itemType identifier.
     *          The JPanel is then added to the frame
     */
    public void createAnItemListPanel(Integer itemType) {
        List<String> itemTypeArray = new ArrayList<>();
        JLabel itemPanel = new JLabel();
        itemPanel.setBounds(300, MEMBERSHIP_PANEL_HEIGHT, 300, MID_PANEL_HEIGHT);
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        itemPanel.setLayout(new FlowLayout());

        // For given item type, add menu items to array
        for (model.MenuItem i : MenuItem.values()) {
            if (i.getItemType() == itemType) {
                itemTypeArray.add(i.getName());
            }
        }

        createItemListButtonArray(itemPanel, itemTypeArray);

        itemPanel.setVisible(false);
        itemPanel.setIcon(new ImageIcon("./data/img/"
                + pallets[randomPalletNumber] + "/orderPageBackground.png"));

        itemPanelList.add(itemPanel);
        frame.add(itemPanel);
    }

    /*
     * MODIFIES: this, itemPanel
     * EFFECTS: Creates an array of item list buttons based on the itemTypeArray then adds it to the itemPanel.
     *          Adds the array of itemButton to the list of item buttons
     */
    public void createItemListButtonArray(JLabel itemPanel, List<String> itemTypeArray) {
        JButton[] itemButtons = new JButton[itemTypeArray.size()];
        for (int i = 0; i < itemTypeArray.size(); i++) {
            itemButtons[i] = new JButton(itemTypeArray.get(i));
            itemButtons[i].setPreferredSize(new Dimension(ITEM_TYPE_BUTTON_WIDTH, ITEM_TYPE_BUTTON_HEIGHT));
            itemButtons[i].setFocusable(false);
            itemButtons[i].setBounds(ITEM_PANEL_HORIZONTAL_BUTTON_SPACER, ITEM_PANEL_VERTICAL_BUTTON_SPACER * i,
                    ITEM_PANEL_BUTTON_WIDTH, ITEM_PANEL_BUTTON_HEIGHT);
            itemButtons[i].addActionListener(this);
            itemPanel.add(itemButtons[i]);
        }
        listOfItemButtons.add(itemButtons);
    }

    /*
     * EFFECTS: Creates an Item List Panel for each Item Type
     */
    public void createAllItemListPanels() {
        for (int i = 0; i < getPossibleItemTypes(); i++) {
            createAnItemListPanel(i + 1);
        }
    }

    /*
     * EFFECTS: returns index of visible panel in the list of Item Panels otherwise returns -1
     */
    public int getVisibleItemListPanel() {
        for (int i = 0; i < getPossibleItemTypes(); i++) {
            if (itemPanelList.get(i).isVisible()) {
                return i;
            }
        }
        return -1;
    }

    /*
     * EFFECTS: returns the size of all possible unique Item Types
     */
    public int getPossibleItemTypes() {
        List<Integer> itemTypes = new ArrayList<>();
        for (model.MenuItem i : MenuItem.values()) {
            if (!(itemTypes.contains(i.getItemType()))) {
                itemTypes.add(i.getItemType());
            }
        }
        return itemTypes.size();
    }

    /*
     * ----------RECEIPT PANEL----------------------------------
     * EFFECTS: Creates the Receipt Panel framework
     */
    public void createReceiptPanel() {
        receiptPanel.setBounds(600, MEMBERSHIP_PANEL_HEIGHT, RECEIPT_PANEL_WIDTH, MID_PANEL_HEIGHT);
        receiptPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        receiptPanel.setLayout(new FlowLayout());
        createReceiptListPane();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Creates the receipt list pane
     *          Given a list of items in the receipt, create one JTextField for the name, a second for the price of item
     *          and a JButton all in one row.  The button deletes the item from the list
     */
    public void createReceiptListPane() {
        receiptDeleteButtonList = new ArrayList<>();
        enclosure = new JPanel();
        enclosure.setLayout(new GridBagLayout());

        createReceiptListLines();

        enclosure.setBackground(new Color(palletHex[randomPalletNumber]));

        receiptScrollPane = new JScrollPane(enclosure);
        receiptScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        receiptScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        receiptScrollPane.setBounds(5, 5, 350, 300);
        receiptScrollPane.setPreferredSize(new Dimension(350, 300));
        receiptScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        receiptPanel.add(receiptScrollPane);
        froyoPicture = createBaseFlavourImage();
        receiptPanel.add(froyoPicture);
        receiptPanel.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Given a list of items in the receipt, create one JTextField for the name,
     *          a second for the price of item and a JButton all in one row.
     *          The button deletes the item from the list
     */
    public void createReceiptListLines() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        int indexCount = 0;
        for (String s : receiptList) {

            JTextField itemNameField = setReceiptListTextField((indexCount + 1) + ". " + s);
            c.anchor = GridBagConstraints.PAGE_START;
            c.weightx = 0.5;
            c.gridy = indexCount;
            c.gridx = 0;
            enclosure.add(itemNameField, c);

            JTextField itemPriceField = setReceiptListTextField("$" + String.format("%.2f", getMenuItemPrice(s)));
            c.gridx = 1;
            enclosure.add(itemPriceField, c);

            JButton deleteButton = new JButton("X");
            deleteButton.setBounds(0, 0, 15, 15);
            deleteButton.addActionListener(this);
            deleteButton.setFocusable(false);
            c.weightx = 0.1;
            receiptDeleteButtonList.add(deleteButton);
            c.gridx = 2;
            enclosure.add(deleteButton, c);

            indexCount++;
        }
    }

    /*
     * EFFECTS: Creates a JTextField given passed textName and sets its editable to false and then return it.
     */
    public JTextField setReceiptListTextField(String textName) {
        JTextField textField = new JTextField(textName);
        textField.setEditable(false);
        return textField;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Creates the frozen yogurt images
     */
    public JPanel createBaseFlavourImage() {
        List<String> frozenYogurtColors = new ArrayList<>();
        JPanel testPanel = new JPanel();
        testPanel.setVisible(false);
        int index = 0;
        for (String s : receiptList) {
            for (int i = 0; i < MenuItem.values().length; i++) {
                if (MenuItem.values()[i].getName().equals(s) && MenuItem.values()[i].getItemType() == 1) {
                    frozenYogurtColors.add(index, MenuItem.values()[i].getColor());
                    index++;
                }
            }
            if (index == 3) {
                break;
            }
        }
        if (frozenYogurtColors.size() == 3) {
            return organizeFrozenYogurtImage(3, frozenYogurtColors);
        } else if (frozenYogurtColors.size() == 2) {
            return organizeFrozenYogurtImage(2, frozenYogurtColors);
        } else if (frozenYogurtColors.size() == 1) {
            return organizeFrozenYogurtImage(1, frozenYogurtColors);
        }
        return testPanel;
    }

    /*
     * EFFECTS: Depending on the number of base flavours added to the order, the first 3 base flavours will be displayed
     *          on the image and the alignments set.
     */
    public JPanel organizeFrozenYogurtImage(int numOfYogurts, List<String> frozenYogurtColors) {
        Float[] frozenYogurtLocation = {0.25f, 0.5f, 0f};

        JPanel frozenYogurtImagePanel = new JPanel();
        frozenYogurtImagePanel.setOpaque(false);
        frozenYogurtImagePanel.setLayout(new OverlayLayout(frozenYogurtImagePanel));

        for (int i = 0; i < numOfYogurts; i++) {
            JLabel image = new JLabel();
            image.setIcon(new ImageIcon("./data/img/frozenYogurt/" + frozenYogurtColors.get(i) + ".png"));
            image.setAlignmentX(frozenYogurtLocation[i]);
            frozenYogurtImagePanel.add(image);
        }
        return frozenYogurtImagePanel;
    }

    /*
     * EFFECTS: Gets an itemName and searches MenuItem for value match. Returns the price of the entered item.
     */
    public double getMenuItemPrice(String itemName) {
        for (int i = 0; i < MenuItem.values().length; i++) {
            if (MenuItem.values()[i].getName().equals(itemName)) {
                return MenuItem.values()[i].getPrice();
            }
        }
        return 0;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the given text from JButton to the receipt list then updates the ListPane and TotalsPane
     */
    public void addItemToReceipt(JButton itemButton) {
        receiptList.add(itemButton.getText());
        for (int i = 0; i < MenuItem.values().length; i++) {
            if (MenuItem.values()[i].getName().equals(itemButton.getText())) {
                myOrder.addItem(MenuItem.values()[i]);
            }
        }
        updateListPane();
        updateTotals();
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes an item from receipt by removing the corresponding entries in receiptList and the
     *          associated delete button, then updates the ListPane and TotalsPane
     */
    public void removeItemFromReceipt(int deleteButtonIndex) {
        if (deleteButtonIndex <= receiptList.size()) {
            receiptList.remove(deleteButtonIndex);
            receiptDeleteButtonList.remove(deleteButtonIndex);
        }
        myOrder.removeItem(deleteButtonIndex);

        updateListPane();
        updateTotals();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Updates the receiptListPane by:
     *          Removing the current scrollable receipt list pane, refreshing the receipt panel,
     *          then calling createReceiptListPane() to recreate the receipt list pane
     */
    public void updateListPane() {
        receiptPanel.remove(receiptScrollPane);
        receiptPanel.remove(froyoPicture);
        receiptPanel.revalidate();
        receiptPanel.repaint();

        createReceiptListPane();
    }

    /*
     * ----------TOTALS PANEL-----------------------------------
     * MODIFIES: this
     * EFFECTS: Creates the total pane showing subtotal, tax, total.
     */
    public void createTotalsPane() {
        totalsPanel.setBounds(0, MEMBERSHIP_PANEL_HEIGHT + MID_PANEL_HEIGHT, FRAME_WIDTH, TOTALS_PANEL_HEIGHT);
        totalsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalsPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        subtotalLabel = setTotalPaneLabels("Subtotal: $" + 0.00);
        c = setGridBagConstraints(0, 0, 1, 0);
        totalsPanel.add(subtotalLabel, c);

        taxLabel = setTotalPaneLabels("Tax: $" + 0.00);
        c = setGridBagConstraints(0, 1, 1, 0);
        totalsPanel.add(taxLabel, c);

        orderTotalLabel = setTotalPaneLabels("Total: $" + 0.00);
        c = setGridBagConstraints(0, 2, 1, 0);
        totalsPanel.add(orderTotalLabel, c);

        payButton = new JButton("PAY");
        payButton.setFocusable(false);
        payButton.addActionListener(this);
        c = setGridBagConstraints(1, 0, 0.5, 1);
        c.gridheight = 4;
        c.insets = new Insets(0, 0, 0, 50);
        c.anchor = GridBagConstraints.LINE_END;
        totalsPanel.add(payButton, c);
    }

    /*
     * EFFECTS: Creates a JLabel and sets the text to the given labelText, sets the font, and then returns it
     */
    public JLabel setTotalPaneLabels(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font(null, Font.PLAIN, 15));
        return label;
    }

    /*
     * EFFECTS: Creates a GridBagConstraint with given constraints and returns it.
     */
    public GridBagConstraints setGridBagConstraints(int gridx, int gridy, double weightx, double weighty) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.weightx = weightx;
        c.weighty = weighty;

        return c;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Calls methods for Totals values then sets the labels for the Totals
     */
    public void updateTotals() {
        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", myOrder.getSubTotal()));
        taxLabel.setText("Tax: $" + String.format("%.2f", myOrder.getSubTotal() * TOTAL_TAX));
        orderTotalLabel.setText("Total: $" + String.format("%.2f", myOrder.getSubTotal() * (1 + TOTAL_TAX)));
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds reward points to member data,
     *          adds order to master list of orders,
     *          adds member to master list of members,
     *          sets current customer to the order,
     *          add the price of this order to the total spent value of the member
     *          Tells user that the order has been paid.
     *          Disposes of order frame
     */
    private void payOrderAddToDatabase() {
        if (!(myOrder.getOrderSize() == 0 || myOrder.getList() == null)) {
            int intSubTotal = (int) myOrder.getSubTotal();
            member.addPoints(intSubTotal * REWARD_MULTIPLIER);
            orderList.addOrderToOrderList(myOrder);
            memberList.addMemberToMemberList(member);
            myOrder.setMemberID(member.getMemberID());
            member.addTotalSpent(myOrder.getSubTotal() * (1 + TOTAL_TAX));

            showMessageDialog(null, "Order Paid!");
        }
        frame.dispose();
    }

    /*
     * ----------CREATE ORDER FRAME-----------------------------
     * MODIFIES: this
     * EFFECTS: Adds the individual panels to the Order Frame and packs
     */
    public void createOrderFrame() {

        frame.add(membershipPanel, BorderLayout.NORTH);
        frame.add(totalsPanel, BorderLayout.SOUTH);
        frame.add(receiptPanel, BorderLayout.EAST);
        frame.add(itemTypePanel, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    /*
     * This method references code from Wayan at KodeJava
     * Link: "https://kodejava.org/how-do-i-validate-input-when-using-scanner/#:~:text=To%20validate
     *                                 %20input%20the%20Scanner,provide%20a%20positive%20integer%20number."
     *
     * EFFECTS: Continuously scans user input and checks if it is an integer >=0.  Returns valid user int input.
     *          Method is heavily modified to fit the GUI
     */
    static int validateIntScannerInput(String dialogue) throws CancelledOrderException {
        int number;
        do {
            String userInput = JOptionPane.showInputDialog(null, dialogue);
            if (userInput == null) {
                showMessageDialog(null, "Cancelled Order");
                throw new CancelledOrderException();
            }
            try {
                number = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                number = -2;
                showMessageDialog(null, "Please enter a valid number");
            }
        } while (number < 0);
        return number;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // For any item type button pressed, sets item list panel visibility to true and hide other one if open
        for (int i = 0; i < getPossibleItemTypes(); i++) {
            if (e.getSource() == itemTypeButtons[i]) {
                // Calls for the visible itemListPanel and sets it to false
                // Then sets the user chosen panel to true
                if (!(getVisibleItemListPanel() == -1)) {
                    itemPanelList.get(getVisibleItemListPanel()).setVisible(false);
                }
                itemPanelList.get(i).setVisible(true);
            }
        }

        // For ItemLists
        if (getVisibleItemListPanel() >= 0) {
            for (int j = 0; j < listOfItemButtons.get(getVisibleItemListPanel()).length; j++) {
                if (e.getSource() == listOfItemButtons.get(getVisibleItemListPanel())[j]) {
                    addItemToReceipt(listOfItemButtons.get(getVisibleItemListPanel())[j]);
                }
            }
        }

        // For delete buttons
        for (int d = 0; d < receiptDeleteButtonList.size(); d++) {
            if (e.getSource() == receiptDeleteButtonList.get(d)) {
                removeItemFromReceipt(d);
            }
        }

        // For payButton
        if (e.getSource() == payButton) {
            payOrderAddToDatabase();
        }

    }


}
