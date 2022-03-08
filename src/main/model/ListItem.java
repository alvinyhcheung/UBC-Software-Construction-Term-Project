package model;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

// Represents a List of items
public class ListItem {

    private HashMap<Integer, ListItem> masterList;

    /*
     * EFFECTS: Constructs an empty list of items
     */
    public ListItem() {
        masterList = new HashMap<>();
    }

    /*
     * REQUIRES: listItemKey must be a valid number associated with a ListItem
     * EFFECTS: Return the ListItem given a listItemKey
     */
    public ListItem getItem(int listItemKey) {
        return masterList.get(listItemKey);
    }

    /*
     * EFFECT: Returns true if masterList contains listItemKey, else return false
     */
    public boolean containsItem(int listItemKey) {
        return masterList.containsKey(listItemKey);
    }

    /*
     * EFFECT: Returns size of masterList
     */
    public int length() {
        return masterList.size();
    }

    /*
     * REQUIRES: member contains a valid MemberID
     * MODIFIES: this
     * EFFECTS:  adds the provided member to the memberList with its memberID as key
     */
    public void addMemberToMemberList(Member listItem) {
        masterList.put(listItem.getMemberID(), listItem);
        EventLog.getInstance().logEvent(new Event("Added " + listItem.getMemberName()
                + " to master member list"));
    }

    /*
     * REQUIRES: order contains a valid orderID
     * MODIFIES: this
     * EFFECTS: adds the provided order to the orderList with its orderID as key
     */
    public void addOrderToOrderList(Order listItem) {
        masterList.put(listItem.getOrderNumber(), listItem);
        EventLog.getInstance().logEvent(new Event("Added " + listItem.getOrderNumber()
                + " to master order list"));
    }

    /*
     *Placeholder methods that are overridden in the Member class by extension
     */
    public String getMemberName() {
        return null;
    }

    public int getMemberID() {
        return 0;
    }

    public int getRewardPoints() {
        return 0;
    }

    public double getTotalSpent() {
        return 0;
    }


    /*
     *Placeholder methods that are overridden in the Order class by extension
     */
    public int getOrderNumber() {
        return 0;
    }

    public int getOrderSize() {
        return 0;
    }

    public List<MenuItem> getList() {
        return null;
    }

    /*
     * EFFECTS: puts the specified ListItem object into a JSON file and return it
     */
    public JSONObject toJson(String nameOfJsonObject) {
        JSONObject json = new JSONObject();
        json.put(nameOfJsonObject, masterList);
        EventLog.getInstance().logEvent(new Event("Saved " + nameOfJsonObject + " to file"));
        return json;
    }

    public static void logLoadedData() {
        EventLog.getInstance().logEvent(new Event("Loaded master member list and master order list "
                + "from file"));
    }


}
