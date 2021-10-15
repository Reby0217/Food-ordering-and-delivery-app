package ui;

import model.Food;
import model.FoodToOrderList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// Food ordering and delivery app
public class OrderFoodApp {
    private FoodToOrderList foodToOrderList;
    private Food salad;
    private Food onionRings;
    private Food pokeBowl;
    private Scanner input;

    //EFFECTS: runs the ordering food application
    public OrderFoodApp() throws ParseException {
        runOrderFoodApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runOrderFoodApp() throws ParseException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("5")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    //MODIFIES: this
    //EFFECTS: initializes foodToOrderList, food, and input
    private void init() {
        foodToOrderList = new FoodToOrderList();
        salad = new Food("Salad", 11);
        onionRings = new Food("Onion Rings", 6);
        pokeBowl = new Food("Poke Bowl", 15);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: shows the menu of choices to user
    private void displayMenu() {
        System.out.println("Please select from:");
        System.out.println("[1] Add a food item to your food-to-order list");
        System.out.println("[2] Remove a food item from your food-to-order list");
        System.out.println("[3] Set a delivered time");
        System.out.println("[4] View your current order summary");
        System.out.println("[5] Quit the App");
    }

    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doAddFood();
        } else if (command.equals("2")) {
            doRemoveFood();
        } else if (command.equals("3")) {
            doSetTime();
        } else if (command.equals("4")) {
            doViewSummary();
        } else {
            System.out.println("Invalid selection...");
        }
    }

    //MODIFIES: this
    //EFFECTS: adds food to the food to order list
    private void doAddFood() {
        separatorLine();
        System.out.println("Please select your food from:");
        displayFoodMenu();
        String command = "";
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("s")) {
            foodToOrderList.addFood(salad);
        } else if (command.equals("o")) {
            foodToOrderList.addFood(onionRings);
        } else if (command.equals("p")) {
            foodToOrderList.addFood(pokeBowl);
        } else {
            System.out.println("Invalid food option...\n");
        }
        separatorLine();
    }


    //MODIFIES: this
    //EFFECTS: removes the food from the food to order list
    private void doRemoveFood() {
        String command = "";
        separatorLine();
        System.out.println("Please delete your food from:");
        displayFoodMenu();

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("s")) {
            foodToOrderList.removeFood(salad);
        } else if (command.equals("o")) {
            foodToOrderList.removeFood(onionRings);
        } else if (command.equals("p")) {
            foodToOrderList.removeFood(pokeBowl);
        } else {
            System.out.println("Invalid food option...\n");
        }
        separatorLine();
    }

    //EFFECTS: displays the food menu to user
    private void displayFoodMenu() {
        System.out.println("\ts -> " + salad.getName() + "($" + salad.getPrice() + ")");
        System.out.println("\to -> " + onionRings.getName() + "($" + onionRings.getPrice() + ")");
        System.out.println("\tp -> " + pokeBowl.getName() + "($" + pokeBowl.getPrice() + ")");
    }

    //MODIFIES: this
    //EFFECTS: sets the delivered time of this order
    private void doSetTime() {
        foodToOrderList.getDeliveredTime();
        System.out.println("Enter your preferred delivered time in XX:XX form (24-hour-clock)");

        String time = input.next();
        if (!foodToOrderList.isValidTime(time)) {
            System.out.println("Invalid time. Time is set to 00:00 as default.\n");
        } else {
            foodToOrderList.setDeliveredTime(time);
        }
        separatorLine();
    }


    //EFFECTS: displays the summary of the order, including all added food, total number of food that user
    //         added, total price of all added food, and the delivered time of this order
    private void doViewSummary() {
        separatorLine();
        doViewFoodInList();
        System.out.println("Total number of food: " + foodToOrderList.getTotalFoodNum() + "\n");
        System.out.println("Total price of added food: $" + foodToOrderList.getTotalPrice() + "\n");
        System.out.println("Delivered time: " + foodToOrderList.getDeliveredTime());
        separatorLine();
    }

    //EFFECTS: displays all food with price in the current food-to-order list
    private void doViewFoodInList() {
        System.out.println("All food added to your food-to-order list:");
        ArrayList<Food> foodInList = foodToOrderList.getFoodList();
        for (Food food: foodInList) {
            System.out.println("\t" + food.getName() + "($" + food.getPrice() + ")");
        }
        System.out.println("\r");
    }

    //EFFECTS: make separator line, make console base easier to check.
    private void separatorLine() {
        System.out.println("__________________________________________________________");
    }


}
