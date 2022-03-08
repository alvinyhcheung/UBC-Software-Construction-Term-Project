package ui;

import model.ListItem;
import persistence.JsonReader;

import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

// Represents a loader to load data from JASON_STORE into the application
public class Loader {

    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/masterlist.json";


    /*
     * ----------LOADER-----------------------------------------
     * EFFECTS: Constructs loader object
     */
    public Loader() {
    }

    /*
     * EFFECTS: Reads data corresponding to memberList and returns the ListItem
     */
    public ListItem loadMemberList(ListItem passedList) {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            passedList = jsonReader.read("memberList");
        } catch (IOException exception) {
            showMessageDialog(null, "Could not read the file. Please try again");
        }
        return passedList;
    }

    /*
     * EFFECTS: Reads data corresponding to orderList and returns the ListItem
     */
    public ListItem loadOrderList(ListItem passedList) {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            passedList = jsonReader.read("orderList");
        } catch (IOException exception) {
            showMessageDialog(null, "Could not read the file. Please try again");
        }
        return passedList;
    }


}
