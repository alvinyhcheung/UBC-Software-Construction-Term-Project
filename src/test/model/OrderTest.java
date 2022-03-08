package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    Order testOrder;

    @BeforeEach
    public void runBefore() {
        testOrder = new Order();
    }

    /*
     * Also tests for getList on non-empty order
     * Also tests for multiple of the same item in an order is valid
     */
    @Test
    public void testAddItem() {
        testOrder.addItem(MenuItem.chocolate);
        testOrder.addItem(MenuItem.vanilla);
        testOrder.addItem(MenuItem.blueberries);
        assertEquals(testOrder.getOrderSize(), 3);
        assertEquals(testOrder.getList().get(0).getName(), "Chocolate Yogurt");
        assertEquals(testOrder.getList().get(0).getPrice(), 2.00);
        assertEquals(testOrder.getList().get(0).getItemType(), 1);
        assertEquals(testOrder.getList().get(1).getName(), "Vanilla Yogurt");
        assertEquals(testOrder.getList().get(1).getPrice(), 2.00);
        assertEquals(testOrder.getList().get(1).getItemType(), 1);
        assertEquals(testOrder.getList().get(2).getName(), "Blueberries");
        assertEquals(testOrder.getList().get(2).getPrice(), 1.10);
        assertEquals(testOrder.getList().get(2).getItemType(), 2);
    }

    @Test
    public void testEmptyGetList() {
        assertEquals(testOrder.getList().size(), 0);
    }

    @Test
    public void testRemoveItem() {
        testOrder.addItem(MenuItem.chocolate);
        testOrder.addItem(MenuItem.vanilla);
        testOrder.addItem(MenuItem.strawberry);
        testOrder.addItem(MenuItem.strawberry);
        testOrder.addItem(MenuItem.cherry);

        testOrder.removeItem(3);

        assertEquals(testOrder.getOrderSize(), 4);
        assertEquals(testOrder.getList().get(0).getName(), "Chocolate Yogurt");
        assertEquals(testOrder.getList().get(0).getPrice(), 2.00);
        assertEquals(testOrder.getList().get(1).getName(), "Vanilla Yogurt");
        assertEquals(testOrder.getList().get(1).getPrice(), 2.00);
        assertEquals(testOrder.getList().get(2).getName(), "Strawberry Yogurt");
        assertEquals(testOrder.getList().get(2).getPrice(), 2.00);
        assertEquals(testOrder.getList().get(3).getName(), "Cherry Sorbet");
        assertEquals(testOrder.getList().get(3).getPrice(), 2.75);
    }

    @Test
    public void testSetOrderNumberGetOrderNumber() {
        testOrder.setOrderNumber(5);
        assertEquals(testOrder.getOrderNumber(), 5);
    }

    @Test
    public void testSetMemberIDGetMemberID() {
        testOrder.setMemberID(1);
        assertEquals(testOrder.getMemberID(), 1);
    }

    @Test
    public void testGetSubTotalFilled() {
        testOrder.addItem(MenuItem.chocolate);
        testOrder.addItem(MenuItem.vanilla);
        testOrder.addItem(MenuItem.strawberry);
        testOrder.addItem(MenuItem.strawberry);
        testOrder.addItem(MenuItem.blueberries);

        assertEquals(testOrder.getSubTotal(), 9.10);
    }

    @Test
    public void testGetSubTotalEmpty() {
        assertEquals(testOrder.getSubTotal(), 0);
    }

    @Test
    public void testSetOrderList() {
        List<MenuItem> itemList = new ArrayList<>();
        itemList.add(MenuItem.chocolate);
        itemList.add(MenuItem.vanilla);
        itemList.add(MenuItem.strawberries);

        testOrder.setOrderList(itemList);

        assertEquals(testOrder.getOrderSize(), 3);
        assertEquals(testOrder.getList(), itemList);
    }

    @Test
    public void testGetColor() {
        testOrder.addItem(MenuItem.chocolate);
        testOrder.addItem(MenuItem.vanilla);

        assertEquals(testOrder.getList().get(0).getColor(), "brown");
        assertEquals(testOrder.getList().get(1).getColor(), "white");
    }

}
