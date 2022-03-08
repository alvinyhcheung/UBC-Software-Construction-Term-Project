# Checkout Interface for Retail

## A Frozen Yogurt Checkout Interface For Retailers

A frozen yogurt retail checkout interface used by employees or, if you trust them enough, as a self-checkout interface.
Can easily be converted to any simple retail checkout interface.  Keeps a tab on every customer by a membership.



### Key Features Include:

- Selecting Items
    - Frozen Yogurt
    - Fresh fruit toppings
    - Yummy toppings
    - Syrups and sauces
- Remove items from a current order
- Displaying up-to-date itemized order
- Display Sub-total, tax, and total
- Membership
  - Create new members
  - Add reward points to a member
  - Allow returning members to add to current reward points on file
  - Shows membership info of any member

## User Stories

- As a user, I want to be able to begin a retail transaction
- As a user, I want to be able to add items to the current customer's order list
- As a user, I want to be able to remove items to the current customer's order list
- As a user, I want to be able to view the list of items on a customer's order list
- As a user, I want to be able to view the sub-total, tax, and total price of the customer's order
- As a user, I want to be able to complete an order by asking for payment and printing the customer a receipt
- As a user, I want to be able to add a customer as a member to start collecting reward points if they are not already a member
- As a user, I want to be able to continue adding reward points to an existing member for their new order
- As a user, I want to be able to view the information of any member
- As a user, I want to be able to save the master list of order and members
- As a user, I want to be able to load a master list of orders
- As a user, I want to be able to load a master list of members



## Phase 4: Task 2
Sat Nov 20 00:13:08 PST 2021 \
&nbsp;&nbsp;&nbsp;&nbsp; Created new member with name: Alvin, and memberID: 1\
Sat Nov 20 00:13:08 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Created new Order\
Sat Nov 20 00:13:09 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added Vanilla Yogurt\
Sat Nov 20 00:13:10 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added Strawberry Yogurt\
Sat Nov 20 00:13:11 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added Coconut Shavings\
Sat Nov 20 00:13:12 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added Dark Chocolate Chips\
Sat Nov 20 00:13:12 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added White Chocolate Chips\
Sat Nov 20 00:13:13 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Removed White Chocolate Chips\
Sat Nov 20 00:13:13 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added 20 to member's reward totals\
Sat Nov 20 00:13:13 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added 1 to master order list\
Sat Nov 20 00:13:13 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added Alvin to master member list\
Sat Nov 20 00:13:13 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Added 5.264 to member's order totals\
Sat Nov 20 00:13:15 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Saved memberList to file\
Sat Nov 20 00:13:15 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Saved orderList to file\
Sat Nov 20 00:13:23 PST 2021\
&nbsp;&nbsp;&nbsp;&nbsp; Quit program

## Phase 4: Task 3
- Lots of coupling around the ListItem class. Look to decouple this area by maybe implementing the Observer Design
- UML does not show what I had in mind for the app design.  I wanted LaunchPage to be associated with a MembershipPanel 
and an OrderPanel, but instead they are dependent instead of associations. These goes back to the coupling around 
ListItem. Could look to instantiate these panels as fields instead of just dependencies.
- Loader class has no field for a JsonReader and instead has a dependency on JsonReader depending on which method 
is called so its association is currently 0...1.  It makes more sense to just have an association of 1 like the 
Saver class so maybe refactor to fix that.
- OrderPanel class seems a bit over encumbered and may consider splitting into subclasses
- Member and Order class are similar, so they extend a ListItem class, but it looks like I may be able to
introduce an abstraction for these two classes.
- Explore the potential of Java.Swing to improve UX.
