package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * This JsonWriterTest class references code from CPSC210 JsonSerializationDemo
 * Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */


public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            ListItem li = new ListItem();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testWriterEmptyListItem() {
        try {
            ListItem testMemberList = new ListItem();
            ListItem testOrderList = new ListItem();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListItem.json");
            writer.open();
            writer.write(testMemberList, testOrderList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListItem.json");
            testMemberList = reader.read("memberList");
            testOrderList = reader.read("orderList");

            assertEquals(0, testMemberList.length());
            assertEquals(0, testOrderList.length());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



    @Test
    public void testWriterGeneralListItem() {
        try {
            ListItem testMemberList = new ListItem();
            Member testMember1 = new Member("TestName1", 1);
            testMember1.addPoints(10);
            testMember1.addTotalSpent(14.5);
            Member testMember2 = new Member("TestName2", 2);
            testMember2.addPoints(30);
            testMember2.addTotalSpent(19.5);
            testMemberList.addMemberToMemberList(testMember1);
            testMemberList.addMemberToMemberList(testMember2);

            ListItem testOrderList = new ListItem();
            Order testOrder1 = new Order();
            testOrder1.setOrderNumber(1);
            testOrder1.setMemberID(1);
            testOrder1.addItem(MenuItem.chocolate);
            testOrder1.addItem(MenuItem.vanilla);
            Order testOrder2 = new Order();
            testOrder2.setOrderNumber(2);
            testOrder2.setMemberID(2);
            testOrder2.addItem(MenuItem.butterscotch);
            testOrder2.addItem(MenuItem.wildBerry);
            testOrderList.addOrderToOrderList(testOrder1);
            testOrderList.addOrderToOrderList(testOrder2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListItem.json");
            writer.open();
            writer.write(testMemberList, testOrderList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListItem.json");
            testMemberList = reader.read("memberList");
            testOrderList = reader.read("orderList");

            List<MenuItem> testOrderItemList1 = new ArrayList<>();
            testOrderItemList1.add(MenuItem.chocolate);
            testOrderItemList1.add(MenuItem.vanilla);

            List<MenuItem> testOrderItemList2 = new ArrayList<>();
            testOrderItemList2.add(MenuItem.butterscotch);
            testOrderItemList2.add(MenuItem.wildBerry);

            assertEquals("TestName1",testMemberList.getItem(1).getMemberName());
            assertEquals(14.5,testMemberList.getItem(1).getTotalSpent());
            assertEquals(10,testMemberList.getItem(1).getRewardPoints());
            assertEquals(1,testMemberList.getItem(1).getMemberID());
            assertEquals("TestName2",testMemberList.getItem(2).getMemberName());
            assertEquals(19.5,testMemberList.getItem(2).getTotalSpent());
            assertEquals(30,testMemberList.getItem(2).getRewardPoints());
            assertEquals(2,testMemberList.getItem(2).getMemberID());

            assertEquals(1, testOrderList.getItem(1).getOrderNumber());
            assertEquals(2, testOrderList.getItem(1).getOrderSize());
            assertEquals(testOrderItemList1, testOrderList.getItem(1).getList());

            assertEquals(2, testOrderList.getItem(2).getOrderNumber());
            assertEquals(2, testOrderList.getItem(2).getOrderSize());
            assertEquals(testOrderItemList2, testOrderList.getItem(2).getList());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
