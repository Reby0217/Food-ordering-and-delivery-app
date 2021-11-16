package persistence;

import model.Food;
import model.FoodToOrderList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderTest extends JsonTest {
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodToOrderList ftoList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    public void testReaderEmptyFoodToOrderList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFoodToOrderList.json");
        try {
            FoodToOrderList ftoList = reader.read();
            assertEquals(0, ftoList.getTotalFoodNum());
            assertEquals(0, ftoList.getTotalPrice());
            assertEquals("00:00", ftoList.getDeliveredTime());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralFoodToOrderList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            FoodToOrderList ftoList = reader.read();
            ArrayList<Food> foodList = ftoList.getFoodList();
            assertEquals(2, foodList.size());
            assertEquals(15, ftoList.getTotalPrice());
            assertEquals(2, ftoList.getTotalFoodNum());
            ftoList.setDeliveredTime("12:23");
            assertEquals("12:23", ftoList.getDeliveredTime());
            checkFood("Burger", 13, foodList.get(0));
            checkFood("Coke", 2, foodList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
