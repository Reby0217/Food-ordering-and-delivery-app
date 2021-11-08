package persistence;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


import model.Food;
import model.FoodToOrderList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

//Represents a reader that reads FoodToOrderList from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads food-to-order-list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodToOrderList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseFoodToOrderList(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<java.lang.String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //REQUIRES: deliveredTime is set (not null)
    //EFFECTS: parses food-to-order-list from JSON object and returns it
    private FoodToOrderList parseFoodToOrderList(JSONObject jsonObject) {
        int totalPrice = jsonObject.getInt("totalPrice");
        int totalFoodNum = jsonObject.getInt("totalFoodNum");
        String deliveredTime = jsonObject.getString("deliveredTime");
        FoodToOrderList ftoList = new FoodToOrderList();

        ftoList.setTotalFoodNum(totalFoodNum);

        ftoList.setTotalPrice(totalPrice);

        ftoList.setDeliveredTime(deliveredTime);

        addFoodList(ftoList, jsonObject);
        return ftoList;
    }

    // MODIFIES: ftoList
    // EFFECTS: parses food items from JSON object and adds them to food-to-order-list
    private void addFoodList(FoodToOrderList ftoList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodList");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(ftoList, nextFood);
        }
    }

    // MODIFIES: ftoList
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addFood(FoodToOrderList ftoList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int price = jsonObject.getInt("price");
        Food food = new Food(name, price);
        ftoList.addFood(food);
    }

}
