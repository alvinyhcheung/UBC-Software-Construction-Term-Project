package model;


/*
 * This JsonWriterTest class references code from CPSC210 JsonSerializationDemo
 * Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListItem li = reader.read("memberList");
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyListItem() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListItem.json");
        try {
            ListItem testMemberList = reader.read("memberList");
            ListItem testOrderList = reader.read("orderList");
            assertEquals(0, testMemberList.length());
            assertEquals(0, testOrderList.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderNonExistentListItem() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListItem.json");
        try {
            ListItem testNonExistentList = reader.read("listDoesNotExist");
            assertNull(testNonExistentList);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralListItem() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListItem.json");
        try {
            ListItem testMemberList = reader.read("memberList");
            ListItem testOrderList = reader.read("orderList");

            List<MenuItem> testOrderItemList1 = new ArrayList<>();
            testOrderItemList1.add(MenuItem.chocolate);
            testOrderItemList1.add(MenuItem.vanilla);

            List<MenuItem> testOrderItemList2 = new ArrayList<>();
            testOrderItemList2.add(MenuItem.butterscotch);
            testOrderItemList2.add(MenuItem.wildBerry);

            assertEquals("TestName1", testMemberList.getItem(1).getMemberName());
            assertEquals(14.5, testMemberList.getItem(1).getTotalSpent());
            assertEquals(10, testMemberList.getItem(1).getRewardPoints());
            assertEquals(1, testMemberList.getItem(1).getMemberID());
            assertEquals("TestName2", testMemberList.getItem(2).getMemberName());
            assertEquals(19.5, testMemberList.getItem(2).getTotalSpent());
            assertEquals(30, testMemberList.getItem(2).getRewardPoints());
            assertEquals(2, testMemberList.getItem(2).getMemberID());

            assertEquals(1, testOrderList.getItem(1).getOrderNumber());
            assertEquals(2, testOrderList.getItem(1).getOrderSize());
            assertEquals(testOrderItemList1, testOrderList.getItem(1).getList());

            assertEquals(2, testOrderList.getItem(2).getOrderNumber());
            assertEquals(2, testOrderList.getItem(2).getOrderSize());
            assertEquals(testOrderItemList2, testOrderList.getItem(2).getList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
