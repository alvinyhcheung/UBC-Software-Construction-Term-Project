package model;

import java.util.List;

// Represents a member having a memberID, memberName, rewardPoints, and totalSpent
public class Member extends ListItem {

    protected int memberID;
    protected String memberName;
    protected int rewardPoints = 0;
    protected double totalSpent = 0;

    /*
     * EFFECTS: Constructs a member having a memberID, memberName
     */
    public Member(String name, int memberID) {
        EventLog.getInstance().logEvent(new Event("Created new member with name: " + name
                + ", and memberID: " + memberID));
        this.memberName = name;
        this.memberID = memberID;
    }

    /*
     * REQUIRES: points > 0
     * MODIFIES: this
     * EFFECTS: Adds specified reward points to member's ongoing points
     */
    public void addPoints(int points) {
        EventLog.getInstance().logEvent(new Event("Added " + points + " to member's reward totals"));
        this.rewardPoints += points;
    }

    /*
     * REQUIRES: orderPrice > 0
     * MODIFIED: this
     * EFFECTS: adds orderPrice to the running total spent of a member
     */
    public void addTotalSpent(double orderPrice) {
        EventLog.getInstance().logEvent(new Event("Added " + orderPrice + " to member's order totals"));
        this.totalSpent += orderPrice;
    }


    /*
     * GETTERS
     */
    @Override
    public String getMemberName() {
        return memberName;
    }

    @Override
    public int getMemberID() {
        return memberID;
    }

    @Override
    public int getRewardPoints() {
        return rewardPoints;
    }

    @Override
    public double getTotalSpent() {
        return totalSpent;
    }

}
