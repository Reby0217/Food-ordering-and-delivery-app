package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a food item having a name and a price
public class Food implements Writable {
    private String name;
    private int price;

    //REQUIRES: the length of the foodName is not zero and foodPrice >= 0
    //EFFECTS: sets the name of the food to foodName and sets the price of the food to foodPrice
    public Food(String foodName, int foodPrice) {
        name = foodName;
        price = foodPrice;
    }

    //EFFECTS: returns the name of the food
    public String getName() {
        return name;
    }

    //EFFECTS: returns the price of the food
    public int getPrice() {
        return price;
    }

    //REQUIRES: the length of the name is not zero
    //MODIFIES: this
    //EFFECTS: sets or changes the name of the Food
    public void setName(String name) {
        this.name = name;
    }


    //MODIFIES: this
    //EFFECTS: if the price is non-negative, sets or changes the price of the food.
    //         Otherwise, sets the price to be 999999999.
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 999999999;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Food food = (Food) o;

        if (price != food.price) {
            return false;
        }
        return name.equals(food.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price;
        return result;
    }
}
