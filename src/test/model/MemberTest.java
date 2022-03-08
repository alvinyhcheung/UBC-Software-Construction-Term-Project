package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberTest {

    Member testMember1;
    Member testMember2;

    @BeforeEach
    public void runBefore() {
        testMember1 = new Member("TestName1", 1);
        testMember2 = new Member("TestName2", 2);
    }

    @Test
    public void testGetMemberName() {
        assertEquals(testMember1.getMemberName(), "TestName1");
    }

    @Test
    public void testGetMemberID() {
        assertEquals(testMember1.getMemberID(), 1);
    }

    @Test
    public void testAddPointsGetPoints() {
        testMember1.addPoints(10);
        assertEquals(testMember1.getRewardPoints(), 10);
        assertEquals(testMember2.getRewardPoints(), 0);
    }

    @Test
    public void testAddTotalSpentGetTotalSpent() {
        testMember1.addTotalSpent(11.5);
        testMember1.addTotalSpent(14.5);
        assertEquals(testMember1.getTotalSpent(), 26.0);
    }

}
