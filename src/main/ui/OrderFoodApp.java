package ui;

import model.Food;
import model.FoodToOrderList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Food ordering and delivery app
public class OrderFoodApp {
    private static final String JSON_STORE = "./data/foodToOrderList.json";
    private FoodToOrderList foodToOrderList;
    private Food salad;
    private Food onionRings;
    private Food pokeBowl;
    private Food burger;
    private Food coke;
    private Food appleJuice;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the ordering food application
    public OrderFoodApp() throws FileNotFoundException {
        init();
        runOrderFoodApp();
    }

    //MODIFIES: this
    //EFFECTS: initializes foodToOrderList, food, and input
    private void init() {
        foodToOrderList = new FoodToOrderList();
        salad = new Food("Salad", 11);
        onionRings = new Food("Onion Rings", 6);
        pokeBowl = new Food("Poke Bowl", 15);
        burger = new Food("Burger", 13);
        coke = new Food("Coke", 2);
        appleJuice = new Food("Apple Juice", 3);
        foodToOrderList.setDeliveredTime("00:00");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    private void runOrderFoodApp() {
        boolean keepGoing = true;
        String command = null;


        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("7")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Have a good day! See you next time.");

    }


    //EFFECTS: shows the menu of choices to user
    private void displayMenu() {
        System.out.println("\nPlease select from:");
        System.out.println("\t[1] Add a food item to your food-to-order list");
        System.out.println("\t[2] Remove a food item from your food-to-order list");
        System.out.println("\t[3] Set a delivered time");
        System.out.println("\t[4] View your current order summary");
        System.out.println("\t[5] Save food-to-order list to file");
        System.out.println("\t[6] Load food-to-order list from file");
        System.out.println("\t[7] Quit the APP");
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
        } else if (command.equals("5")) {
            saveFoodToOrderList();
        } else if (command.equals("6")) {
            loadFoodToOrderList();
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
        } else if (command.equals("b")) {
            foodToOrderList.addFood(burger);
        } else if (command.equals("c")) {
            foodToOrderList.addFood(coke);
        } else if (command.equals("a")) {
            foodToOrderList.addFood(appleJuice);
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
        } else if (command.equals("b")) {
            foodToOrderList.removeFood(burger);
        } else if (command.equals("c")) {
            foodToOrderList.removeFood(coke);
        } else if (command.equals("a")) {
            foodToOrderList.removeFood(appleJuice);
        } else {
            System.out.println("Invalid food option...\n");
        }
        separatorLine();
    }

    //EFFECTS: displays the food menu to user
    private void displayFoodMenu() {
        System.out.println("\ts -> " + salad.getName() + " ($" + salad.getPrice() + ")");
        System.out.println("\to -> " + onionRings.getName() + " ($" + onionRings.getPrice() + ")");
        System.out.println("\tp -> " + pokeBowl.getName() + " ($" + pokeBowl.getPrice() + ")");
        System.out.println("\tb -> " + burger.getName() + " ($" + burger.getPrice() + ")");
        System.out.println("\tc -> " + coke.getName() + " ($" + coke.getPrice() + ")");
        System.out.println("\ta -> " + appleJuice.getName() + " ($" + appleJuice.getPrice() + ")");
    }

    //MODIFIES: this
    //EFFECTS: sets the delivered time of this order
    private void doSetTime() {
        foodToOrderList.getDeliveredTime();
        System.out.println("Default time is 00:00 (24-hour-clock).");
        System.out.println("Enter your preferred delivered time in XX:XX form (24-hour-clock)");

        String time = input.next();
        if (!foodToOrderList.isValidTime(time)) {
            System.out.println("Invalid time....\n");
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
        System.out.println("Delivered time in 24-hour clock: " + foodToOrderList.getDeliveredTime());
        separatorLine();
    }

    //EFFECTS: displays all food with price in the current food-to-order list
    private void doViewFoodInList() {
        System.out.println("All food added to your food-to-order list:");
        ArrayList<Food> foodInList = foodToOrderList.getFoodList();
        for (Food food : foodInList) {
            System.out.println("\t" + food.getName() + "($" + food.getPrice() + ")");
        }
        System.out.println("\r");
    }

    //EFFECTS: saves the food-to-order list to file
    private void saveFoodToOrderList() {
        try {
            jsonWriter.open();
            jsonWriter.write(foodToOrderList);
            jsonWriter.close();
            System.out.println("Saved the food-to-order list to " + JSON_STORE + " successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads food-to-order list from file
    private void loadFoodToOrderList() {
        try {
            foodToOrderList = jsonReader.read();
            System.out.println("Loaded the food-to-order list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: prints a separator line to make the console easier to read.
    private void separatorLine() {
        System.out.println("__________________________________________________________");
    }


}
