package ui;

import model.ListItem;
import ui.exceptions.CancelledOrderException;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class MembershipPanel extends JFrame {

    protected ListItem memberList;

    public MembershipPanel(ListItem memberList) throws CancelledOrderException {
        this.memberList = memberList;

        getMembershipID();
    }

    public void getMembershipID() throws CancelledOrderException {
        int memberID;
        memberID = OrderPanel.validateIntScannerInput("Please enter MemberID to check");

        if (memberList.containsItem(memberID)) {
            showMessageDialog(null, "Name: " + memberList.getItem(memberID).getMemberName()
                    + "\nMember ID: " + memberList.getItem(memberID).getMemberID()
                    + "\nReward Point Balance: " + memberList.getItem(memberID).getRewardPoints()
                    + "\nTotal Spent: " + String.format("%.2f", memberList.getItem(memberID).getTotalSpent()));

        } else {
            showMessageDialog(null, "No member with that ID was found.");
        }
    }


}
