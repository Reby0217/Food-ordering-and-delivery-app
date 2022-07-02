# Academic Project

## A food ordering and delivery app

### Abstract

- **Functions**

 *This app can order food and set the food delivered time.*

- **Users**

*Users can be those who want to order the food of the restaurant online and need the food delivery service.*

- **Reason for my interest in this project**

*Due to the Covid-19, many restaurants are close for dine-in and only allow delivery. In addition, many customers
are too busy to go to the restaurants in person. This app can facilitate the life of the customers by saving
their time and making the food of the restaurant that only allows delivery available to them.*

### User Stories

- As a user, I want to be able to add a food item to my food-to-order list.
- As a user, I want to be able to view the list of food on my food-to-order list.
- As a user, I want to be able to set a time that I want the ordered food to be delivered.
- As a user, I want to be able to delete a food item from my food-to-order list.
- As a user, I want to be able to see the total number of food on my food-to-order list.
- As a user, I want to be able to see the total price of the food on my food-to-order list.
- As a user, I want to be able to save my food-to-order list to file.
- As a user, I want to be able to load my food-to-order list from file.

### Phase 4: Task 2

- A representative sample of the events that occur when the program runs: <br />
  &nbsp;

  Wed Nov 24 21:27:00 PST 2021
  <br/>Salad added to food-to-order list <br /> &nbsp;

  Wed Nov 24 21:27:02 PST 2021
  <br/>Onion Rings added to food-to-order list <br /> &nbsp;

  Wed Nov 24 21:27:05 PST 2021
  <br/>Coke added to food-to-order list <br /> &nbsp;

  Wed Nov 24 21:27:07 PST 2021
  <br/>Coke added to food-to-order list <br /> &nbsp;

  Wed Nov 24 21:27:12 PST 2021
  <br/>Coke removed from food-to-order list <br /> &nbsp;

  Wed Nov 24 21:27:17 PST 2021
  <br/>Onion Rings removed from food-to-order list <br /> &nbsp;

  Wed Nov 24 21:27:25 PST 2021
  <br/>Burger added to food-to-order list <br /> &nbsp;

  Wed Nov 24 21:27:32 PST 2021
  <br/>Set delivered time to: 19:53 (in 24-hour-clock) <br /> &nbsp;


- Explanation:

  - If the user does not add food, remove food or set delivered time before quitting the APP, there will
be no events getting logged when the program runs since no events occur.
  - If an error, like NullPointerException, pops up when we run the program such that the program directly ends and 
  the logEvent method will not be called then the event is not logged. 
  - If an error occurs when we click the quit button,
  then there will be no events being printed out since the program directly ends when an error occurs and the method
  for printing out the events will not be called.
  - The events are printed out only if the user clicks the quit button to exit. If the user does not click the 
  quit button to exit, then the method for printing out the events will not be called such that there will be no events 
  being printed out.
  

### Phase 4: Task 3
- I would refactor those GUI classes in the ui package to improve the design. I would create a new
class called GUI and let AddFoodGUI, FoodToOrderListGUI, OrderFoodMainGUI, and SetTimeGUI extend
this new class. I would move the methods that all these GUI classes have in order to set up a window frame 
into that new class, like the method adding buttons to the corresponding window. I would move the field, ftoList, 
into that new class as well since all of these GUI classes in the ui package have the field, ftoList. 
