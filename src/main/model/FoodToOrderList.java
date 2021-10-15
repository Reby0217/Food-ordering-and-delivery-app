package model;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;



//Represents a list of food to be ordered with total food price, total number of food in the list, and delivery time
public class FoodToOrderList {
    private ArrayList<Food> foodList;
    private int totalPrice;
    private int totalFoodNum;
    private String deliveredTime;

    //EFFECTS: initializes a newly created FoodList as an empty list
    public FoodToOrderList() {
        foodList = new ArrayList<>();
    }

    //EFFECTS: returns the list of food
    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    //MODIFIES: this
    //EFFECTS: adds the food to the list of food
    public void addFood(Food food) {
        foodList.add(food);
    }

    //MODIFIES: this
    //EFFECTS: If the food is in the list of food, then removes it from that list of food.
    //         Otherwise, does nothing.
    public void removeFood(Food food) {
        foodList.remove(food);
    }

    //REQUIRES: totalPrice >= 0
    //EFFECTS: returns the sum of the prices of all the food in the list
    public int getTotalPrice() {
        totalPrice = 0;
        for (Food food : foodList) {
            totalPrice = totalPrice + food.getPrice();
        }
        return totalPrice;
    }

    //REQUIRES: numOfFood >= 0
    //EFFECTS: returns the total number of food in the list
    public int getTotalFoodNum() {
        totalFoodNum = 0;
        for (Food food : foodList) {
            totalFoodNum += 1;
        }
        return totalFoodNum;
    }

    //EFFECTS: if the food is contained in the list, returns true. Otherwise, returns false
    public boolean contains(Food food) {
        return foodList.contains(food);
    }

    //EFFECTS: returns the number of food currently the list
    public int size() {
        return foodList.size();
    }

    //EFFECTS: If the delivered time has not been set, returns "00:00" as default time.
    //         Otherwise, returns the delivered time in hh:mm pattern
    public String getDeliveredTime() {
        if (deliveredTime == null) {
            return "00:00";
        }
        return deliveredTime;
    }

    //MODIFIES: this
    //EFFECTS: If the time is invalid, sets delivered time as "00:00" by default time
    //         Otherwise, sets delivered time to be the time in hh:mm pattern.
    public void setDeliveredTime(String time) {
        if (!isValidTime(time)) {
            time = "00:00";
        }
        deliveredTime = time;

    }

    // This method references code from this website
    // Link: https://stackoverflow.com/a/47886041
    //EFFECTS: returns true if the time is in HH:mm pattern (both HH and mm must be 2 digits)
    //         Otherwise, returns false.
    public boolean isValidTime(String time) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalTime.parse(time, timeFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }


}
