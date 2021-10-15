package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    private Food testFood1;
    private Food testFood2;
    private Food testFood3;

    @BeforeEach
    public void setUp() {
        testFood1 = new Food("Salad", 11);
        testFood2 = new Food("Poke Bowl", 15);
        testFood3 = new Food("Calamari", 6);
    }

    @Test
    public void testConstructor() {
        assertEquals("Salad", testFood1.getName());
        assertEquals(11, testFood1.getPrice());

        assertEquals("Poke Bowl", testFood2.getName());
        assertEquals(15, testFood2.getPrice());

        assertEquals("Calamari", testFood3.getName());
        assertEquals(6, testFood3.getPrice());
    }

    @Test
    public void testSetName() {
        assertEquals("Salad", testFood1.getName());

        testFood1.setName("Caesar Salad");
        assertEquals("Caesar Salad", testFood1.getName());
        assertEquals(11, testFood1.getPrice());
    }

    @Test
    public void testSetPrice() {
        assertEquals(15, testFood2.getPrice());

        testFood2.setPrice(13);
        assertEquals(13, testFood2.getPrice());
        assertEquals("Poke Bowl", testFood2.getName());

        testFood3.setPrice(-5);
        assertEquals(999999999, testFood3.getPrice());

        testFood3.setPrice(0);
        assertEquals(0, testFood3.getPrice());
    }
}