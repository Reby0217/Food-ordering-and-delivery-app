package persistence;

import model.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonTest {
    protected void checkFood(String name, int price, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
    }
}
