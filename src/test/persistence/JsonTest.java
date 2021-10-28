package persistence;

import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(String name, int price, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
    }
}
