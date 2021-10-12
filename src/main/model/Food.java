package model;

// Represents a food item having a name and a price
public class Food {
    private String name;
    private double price;

    //REQUIRES: the length of the foodName is not zero and foodPrice >= 0
    //EFFECTS: sets the name of the food to foodName and sets the price of the food to foodPrice
    public Food(String foodName, double foodPrice) {
        name = foodName;
        price = foodPrice;
    }

    //EFFECTS: returns the name of the food
    public String getName() {
        return name;
    }

    //EFFECTS: returns the price of the food
    public double getPrice() {
        return price;
    }

    //REQUIRES: the length of the name is not zero
    //MODIFIES: this
    //EFFECTS: sets or changes the name of the Food
    public void setName(String name) {
        this.name = name;
    }

    //REQUIRES: the price should be greater than 0
    //MODIFIES: this
    //EFFECTS: sets or changes the name of the Food
    public void setPrice(double price) {
        this.price = price;
    }
}
