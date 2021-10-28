package persistence;

import model.FoodToOrderList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

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
    }

}
