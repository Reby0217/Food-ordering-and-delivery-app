package model;

import java.util.ArrayList;

//Represents a list of foods
public class FoodList {
    private ArrayList<Food> foodList;
    private double totalPrice;
    private int numOfFood;
    private String deliveredTime;

    //EFFECTS: initializes a newly created FoodList as an empty list
    public FoodList() {
        foodList = new ArrayList<>();
    }

    //EFFECTS: returns the list of foods
    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    //MODIFIES: this
    //EFFECTS: adds the food to the list of foods
    public void addFood(Food food) {
        foodList.add(food);
    }

    //MODIFIES: this
    //EFFECTS: remove the food from the list of foods
    public void removeFood(Food food) {
        foodList.remove(food);
    }

    //REQUIRES: totalPrice >= 0
    //EFFECTS: returns the sum of the prices of the foods in the list
    public double getTotalPrice() {
        totalPrice = 0;
        for (Food food : foodList) {
            totalPrice = totalPrice + food.getPrice();
        }
        return totalPrice;
    }

    //REQUIRES: numOfFood >= 0
    //EFFECTS: returns the total number of foods in the list
    public int getNumOfFood() {
        numOfFood = 0;
        for (Food food : foodList) {
            numOfFood += 1;
        }
        return numOfFood;
    }

    //EFFECTS: if the food is contained in the list, returns true. Otherwise, returns false
    public boolean contains(Food food) {
        return foodList.contains(food);
    }

    //EFFECTS: returns the number of foods currently the list
    public int length() {
        return foodList.size();
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
