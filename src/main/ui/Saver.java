package ui;

import model.ListItem;
import persistence.JsonWriter;

import java.io.FileNotFoundException;

import static javax.swing.JOptionPane.showMessageDialog;

public class Saver {

    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/masterlist.json";

    public Saver(ListItem memberList, ListItem orderList) {
        jsonWriter = new JsonWriter(JSON_STORE);

        try {
            jsonWriter.open();
            jsonWriter.write(memberList, orderList);
            jsonWriter.close();
            showMessageDialog(null, "Saved list of members and list of orders");
        } catch (FileNotFoundException e) {
            showMessageDialog(null, "Unable to write to file.  File not found.");
        }
    }
}


