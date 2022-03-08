package persistence;

import model.ListItem;
import model.Member;
import model.MenuItem;
import model.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/*
 * This JsonReader class references code from CPSC210 JsonSerializationDemo
 * Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

// Represents a reader that reads ListItem from JSON data stored in file
public class JsonReader {

    private String source;

    /*
     * EFFECTS: Constructs reader to read from source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /*
     * EFFECTS: reads ListItem from file and returns it
     * throws: IOException if an error occurs when reading the file
     */
    public ListItem read(String listName) throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonarray = new JSONArray(jsonData);

        return addList(jsonarray, listName);
    }

    /*
     * EFFECTS: reads the source file as a string and returns it
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    /*
     * REQUIRES: memberList to be written to file first and then orderList written to file
     * EFFECTS: Delegate the method to call depending on the passed string
     */
    public ListItem addList(JSONArray jsonArray, String listName) {
        if (listName.equals("memberList")) {
            return (addMemberList(jsonArray.getJSONObject(0)));
        } else if (listName.equals("orderList")) {
            return (addOrderList(jsonArray.getJSONObject(1)));
        }
        return null;
    }

    /*
     * EFFECTS: parses through memberList for Member from JSON object to create new Member and add to a memberList
     *              then returns it
     */
    public ListItem addMemberList(JSONObject jsonObject) {
        JSONObject jsonMemberListObject = jsonObject.getJSONObject("memberList");
        ListItem jsonMemberList = new ListItem();

        Set<String> memberListKeys = jsonMemberListObject.keySet();
        for (String key : memberListKeys) {
            JSONObject jsonMemberObject = jsonMemberListObject.getJSONObject(key);

            String memberName = jsonMemberObject.getString("memberName");
            int memberID = jsonMemberObject.getInt("memberID");
            int rewardPoints = jsonMemberObject.getInt("rewardPoints");
            double totalSpent = jsonMemberObject.getDouble("totalSpent");

            Member member = new Member(memberName, memberID);
            member.addTotalSpent(totalSpent);
            member.addPoints(rewardPoints);

            jsonMemberList.addMemberToMemberList(member);
        }
        return jsonMemberList;
    }

    /*
     * EFFECTS: parses through orderList for Order from JSON object to create new Order and add to an orderList
     *              then returns it
     */
    public ListItem addOrderList(JSONObject jsonObject) {
        JSONObject jsonOrderListObject = jsonObject.getJSONObject("orderList");
        ListItem jsonOrderList = new ListItem();

        // Iterating through each key of the HashMap, in other words, every object
        Set<String> orderListKeys = jsonOrderListObject.keySet();
        for (String key : orderListKeys) {
            JSONObject jsonOrderObject = jsonOrderListObject.getJSONObject(key);

            int orderNumber = jsonOrderObject.getInt("orderNumber");
            int memberID = jsonOrderObject.getInt("memberID");

            // Iterate through the list of items in an order and create a new list and add to it
            List<MenuItem> itemList = new ArrayList<>();
            JSONArray jsonArrayItemList = jsonOrderObject.getJSONArray("list");
            for (int i = 0; i < jsonArrayItemList.length(); i++) {
                itemList.add(MenuItem.valueOf(jsonArrayItemList.getString(i)));
            }

            Order order = new Order();
            order.setOrderNumber(orderNumber);
            order.setMemberID(memberID);
            order.setOrderList(itemList);

            jsonOrderList.addOrderToOrderList(order);
        }
        return jsonOrderList;
    }

}
