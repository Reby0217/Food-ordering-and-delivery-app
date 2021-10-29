package persistence;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

import model.Food;
import model.FoodToOrderList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    public void testWriterInvalidFile() {
        try {
            FoodToOrderList ftoList = new FoodToOrderList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyFoodToOrderList() {
        try {
            FoodToOrderList ftoList = new FoodToOrderList();
            ftoList.setDeliveredTime("00:00");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodToOrderList.json");
            writer.open();
            writer.write(ftoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodToOrderList.json");
            ftoList = reader.read();
            assertEquals(0, ftoList.getTotalFoodNum());
            assertEquals(0, ftoList.getTotalPrice());
            assertEquals("00:00", ftoList.getDeliveredTime());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralFoodToOrderList() {
        try {
            FoodToOrderList ftoList = new FoodToOrderList();
            ftoList.addFood(new Food("Apple", 1));
            ftoList.addFood(new Food("Poke Bowl", 15));
            ftoList.setTotalPrice(16);
            ftoList.setTotalFoodNum(2);
            ftoList.setDeliveredTime("13:45");

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFoodToOrderList");
            writer.open();
            writer.write(ftoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFoodToOrderList");
            ftoList = reader.read();
            ArrayList<Food> foodList = ftoList.getFoodList();
            assertEquals(2, foodList.size());
            assertEquals(2, ftoList.getTotalFoodNum());
            assertEquals(16, ftoList.getTotalPrice());
            assertEquals("13:45", ftoList.getDeliveredTime());
            checkFood("Apple", 1, foodList.get(0));
            checkFood("Poke Bowl", 15, foodList.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

}
