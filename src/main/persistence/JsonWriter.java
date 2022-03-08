package persistence;

import model.ListItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * This JsonWriter class references code from CPSC210 JsonSerializationDemo
 * Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

// Represents a writer that writes JSON representation of a ListItem to file
public class JsonWriter {
    private static final int TAB = 8;
    private PrintWriter writer;
    private String destination;

    /*
     * EFFECTS: constructs writer to write to destination file
     */
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /*
     * MODIFIES: this
     * EFFECTS:  opens writer; throws FileNotFoundException if destination file cannot be opened for writing
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes JSON representation of memberList and orderList to file
     */
    public void write(ListItem memberList, ListItem orderList) {
        JSONObject jsonMemberList = memberList.toJson("memberList");
        JSONObject jsonOrderList = orderList.toJson("orderList");

        JSONArray jsonCombined = new JSONArray();
        jsonCombined.put(jsonMemberList);
        jsonCombined.put(jsonOrderList);
        saveToFile(jsonCombined.toString(TAB));
    }

    /*
     * MODIFIES: this
     * EFFECTS: closes writer
     */
    public void close() {
        writer.close();
    }

    /*
     * MODIFIES: this
     * EFFECTS: writes string to file
     */
    private void saveToFile(String json) {
        writer.print(json);
    }

}
