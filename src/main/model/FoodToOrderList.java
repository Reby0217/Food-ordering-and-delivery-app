package model;

import java.util.ArrayList;

//Represents a list of food to be ordered with total food price, total number of food in the list, and delivery time
public class FoodToOrderList {
    private ArrayList<Food> foodToOrderList;
    private double totalPrice;
    private int totalFoodNum;
    private String deliveredTime;

    //EFFECTS: initializes a newly created FoodList as an empty list
    public FoodToOrderList() {
        foodToOrderList = new ArrayList<>();
    }

    //EFFECTS: returns the list of food
    public ArrayList<Food> getFoodToOrderList() {
        return foodToOrderList;
    }

    //MODIFIES: this
    //EFFECTS: adds the food to the list of food
    public void addFood(Food food) {
        foodToOrderList.add(food);
    }

    //MODIFIES: this
    //EFFECTS: If the food is in the list of food, then removes it from that list of food.
    //         Otherwise, does nothing.
    public void removeFood(Food food) {
        foodToOrderList.remove(food);
    }

    //REQUIRES: totalPrice >= 0
    //EFFECTS: returns the sum of the prices of all the food in the list
    public double getTotalPrice() {
        totalPrice = 0;
        for (Food food : foodToOrderList) {
            totalPrice = totalPrice + food.getPrice();
        }
        return totalPrice;
    }

    //REQUIRES: numOfFood >= 0
    //EFFECTS: returns the total number of food in the list
    public int getTotalFoodNum() {
        totalFoodNum = 0;
        for (Food food : foodToOrderList) {
            totalFoodNum += 1;
        }
        return totalFoodNum;
    }

    //EFFECTS: if the food is contained in the list, returns true. Otherwise, returns false
    public boolean contains(Food food) {
        return foodToOrderList.contains(food);
    }

    //EFFECTS: returns the number of food currently the list
    public int size() {
        return foodToOrderList.size();
    }

    //EFFECTS: returns the delivered time or a warning (string) to set the delivered time
    public String getDeliveredTime() {
        if (deliveredTime != null) {
            return deliveredTime;
        }
        return "Waiting to set the delivered time";
    }

    //EFFECTS: sets the delivered time to time
    public void setDeliveredTime(String time) {
        deliveredTime = time;
    }

}
