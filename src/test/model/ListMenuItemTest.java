package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListMenuItemTest {

    ListItem testList;
    Member testMember1;
    Member testMember2;

    Order testOrder1;
    Order testOrder2;

    @BeforeEach
    public void runBefore() {
        testMember1 = new Member("TestName1", 1);
        testMember2 = new Member("TestName2", 2);
        testOrder1 = new Order();
        testOrder1.setOrderNumber(1);
        testOrder1.setMemberID(1);
        testOrder2 = new Order();
        testOrder2.setOrderNumber(2);
        testOrder2.setMemberID(2);
        testList = new ListItem();
    }

    @Test
    public void testLengthEmpty() {
        assertEquals(testList.length(), 0);
    }

    @Test
    public void testAddMemberToMemberListAndLength() {
        testList.addMemberToMemberList(testMember1);
        testList.addMemberToMemberList(testMember2);
        assertEquals(testList.length(),2);
    }

    @Test
    public void testAddOrderToOrderListAndLength() {
        testList.addOrderToOrderList(testOrder1);
        testList.addOrderToOrderList(testOrder2);
        assertEquals(testList.length(),2);
    }

    @Test
    public void testGetItemForMemberList() {
        testList.addMemberToMemberList(testMember1);
        testList.addMemberToMemberList(testMember2);

        assertEquals(testList.getItem(1), testMember1);
        assertEquals(testList.getItem(2), testMember2);

    }

    @Test
    public void testGetItemForOrderList() {
        testList.addOrderToOrderList(testOrder1);
        testList.addOrderToOrderList(testOrder2);

        assertEquals(testList.getItem(1), testOrder1);
        assertEquals(testList.getItem(2), testOrder2);
    }

    @Test
    public void testContainsMemberItem() {
        testList.addMemberToMemberList(testMember1);
        testList.addMemberToMemberList(testMember2);
        assertTrue(testList.containsItem(1));
        assertTrue(testList.containsItem(2));
        assertFalse(testList.containsItem(5));
        assertFalse(testList.containsItem(-1));
    }

    @Test
    public void testContainsOrderItem() {
        testList.addOrderToOrderList(testOrder1);
        testList.addOrderToOrderList(testOrder2);
        assertTrue(testList.containsItem(1));
        assertTrue(testList.containsItem(2));
        assertFalse(testList.containsItem(5));
        assertFalse(testList.containsItem(-1));
    }

    /*
     * These below methods are stubs that are overridden in the Member class.  They are tested in the MemberTest class
     * These tests are here to satisfy coverage only.
     */
    @Test
    public void testGetMemberName() {
        testList.addMemberToMemberList(testMember1);
        assertNull(testList.getMemberName());
    }

    @Test
    public void testGetMemberID() {
        testList.addMemberToMemberList(testMember1);
        assertEquals(0, testList.getMemberID());
    }

    @Test
    public void testGetRewardPoints() {
        testList.addMemberToMemberList(testMember1);
        assertEquals(0, testList.getRewardPoints());
    }

    @Test
    public void testGetTotalSpent() {
        testList.addMemberToMemberList(testMember1);
        assertEquals(0, testList.getTotalSpent());
    }

}
