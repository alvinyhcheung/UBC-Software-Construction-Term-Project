package ui;

import model.Event;
import model.EventLog;
import ui.exceptions.CancelledOrderException;
import model.ListItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showOptionDialog;

/*
 * Represents a Launch Page with buttons to create an Order, check membership, save app data, load app data, and quit
 * Additionally stores data representing the app's color scheme.
 * Instantiates a new master list of a member list and an order list
 */
public class LaunchPage extends JFrame implements ActionListener {

    private JFrame frame = new JFrame("Welcome");
    private JButton orderButton = new JButton("Order");
    private JButton membershipButton = new JButton("Membership");
    private JButton saveButton = new JButton("Save Data");
    private JButton loadButton = new JButton("Load Data");
    private JButton quitButton = new JButton("Quit");

    protected static String[] pallets = {"pallet1", "pallet2", "pallet3", "pallet4"};
    protected static Integer[] palletHex = {0xda9fa7, 0xe0c6b0, 0xa9d6e1, 0xda9fa7};
    protected static int randomPalletNumber;

    protected ListItem memberList = new ListItem();
    protected ListItem orderList = new ListItem();

    /*
     * ----------LAUNCH PAGE------------------------------------
     * EFFECTS: Constructs the launch page,
     *          Adds the order, membership, save, load, and quit button to the launch page,
     *          Calls a random number generator to determine which color pallet to use for this run.
     */
    public LaunchPage() {
        setRandomPalletNumber();

        setButtonSpecification(orderButton);
        setButtonSpecification(membershipButton);
        setButtonSpecification(saveButton);
        setButtonSpecification(loadButton);
        setButtonSpecification(quitButton);

        JLabel paddingLabel = new JLabel();
        paddingLabel.setPreferredSize(new Dimension(200, 100));

        frame.setContentPane(new JLabel(new ImageIcon("./data/img/"
                + pallets[randomPalletNumber] + "/launchPageBackground.png")));

        frame.add(paddingLabel);
        frame.add(orderButton);
        frame.add(membershipButton);
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(quitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 450);
        frame.setIconImage(new ImageIcon("./data/img/windowIcon.png").getImage());
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }

    /*
     * MODIFIES: button
     * EFFECTS: Sets the button's preferred dimensions and adds functionality to the passed button.
     */
    public void setButtonSpecification(JButton button) {
        button.setPreferredSize(new Dimension(200, 50));
        button.setFocusable(false);
        button.addActionListener(this);
    }

    /*
     * ----------RANDOM NUMBER----------------------------------
     * MODIFIES: this
     * EFFECTS: sets the randomPalletNumber to a random number corresponding to an available pallet
     */
    public void setRandomPalletNumber() {
        Random rand = new Random();
        int upperBound = pallets.length - 1;
        randomPalletNumber = rand.nextInt(upperBound);
    }

    /*
     * ----------QUIT OPTION------------------------------------
     * MODIFIES: this
     * EFFECTS: Prompts user with two options:
     *              -Save and quit which saves the data and then quits the program or
     *              -Quit this program without saving any data
     */
    public void quitDialogue() {
        String[] options = {"Save and quit", "Quit without saving"};

        int choice = showOptionDialog(frame, "Would you like to save and quit?", "Save and Quit",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            Saver save = new Saver(memberList, orderList);
        }
        System.exit(1);
    }

    public void screenPrinterOnQuit(EventLog el) {
        EventLog.getInstance().logEvent(new Event("Quit program"));
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }


    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {
            try {
                OrderPanel orderPanel = new OrderPanel(memberList, orderList);
            } catch (CancelledOrderException exception) {
                // Good Catch
            }
        }
        if (e.getSource() == membershipButton) {
            try {
                MembershipPanel membershipPanel = new MembershipPanel(memberList);
            } catch (CancelledOrderException ex) {
                // Good Catch
            }
        }

        if (e.getSource() == saveButton) {
            Saver save = new Saver(memberList, orderList);
        }

        if (e.getSource() == loadButton) {
            Loader load = new Loader();
            memberList = load.loadMemberList(memberList);
            orderList = load.loadOrderList(orderList);
            showMessageDialog(null, "Successfully loaded data");
            ListItem.logLoadedData();
        }

        if (e.getSource() == quitButton) {
            screenPrinterOnQuit(EventLog.getInstance());
            quitDialogue();
        }
    }

}
