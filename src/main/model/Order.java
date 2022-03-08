package model;

import java.util.ArrayList;
import java.util.List;

// Represents an order with orderNumber, memberID, and a menuItemList
public class Order extends ListItem {

    private int orderNumber;
    private int memberID;
    private List<MenuItem> menuItemList;

    /*
     * EFFECTS: Constructs an Order with a new menuItemList
     */
    public Order() {
        EventLog.getInstance().logEvent(new Event("Created new Order"));
        menuItemList = new ArrayList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds an Item to the itemList
     */
    public void addItem(MenuItem i) {
        EventLog.getInstance().logEvent(new Event("Added " + i.getName() + " to order"));
        this.menuItemList.add(i);
    }

    /*
     * REQUIRES: non-empty itemList,
     *           itemNumber is in range of the size of itemList and not 0
     * MODIFIES: this
     * EFFECTS: removes an Item from the itemList
     */
    public void removeItem(int itemNumber) {
        EventLog.getInstance().logEvent(new Event("Removed " + menuItemList.get(itemNumber).getName()
                + " from order"));
        this.menuItemList.remove(itemNumber);
    }

    /*
     * REQUIRES: orderNumber not already a key in OrderList HashTable and >0
     * MODIFIES: this
     * EFFECTS: Sets current orderNumber to given orderNumber
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sets the given memberID
     */
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public void setOrderList(List<MenuItem> orderList) {
        this.menuItemList = orderList;
    }
    
    /*
     * GETTERS
     */
    @Override
    public int getMemberID() {
        return this.memberID;
    }

    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public int getOrderSize() {
        return menuItemList.size();
    }

    @Override
    public List<MenuItem> getList() {
        return this.menuItemList;
    }

    /*
     * EFFECTS: Calculates subtotal price of Items in Order
     */
    public double getSubTotal() {
        double subtotal = 0;
        for (MenuItem i : menuItemList) {
            subtotal += i.getPrice();
        }
        return subtotal;
    }


}
