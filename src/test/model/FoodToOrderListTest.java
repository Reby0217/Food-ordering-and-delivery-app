package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodToOrderListTest {
    private FoodToOrderList testList;
    private Food testFoodA;
    private Food testFoodB;
    private Food testFoodC;

    @BeforeEach
    public void setUp() {
        testList = new FoodToOrderList();
        testFoodA = new Food("Onion Rings", 6);
        testFoodB = new Food("Hash Browns", 3);
        testFoodC = new Food("Tuna Salad", 8);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testList.size());
    }

    @Test
    public void testAddFood() {
        assertEquals(0, testList.size());

        testList.addFood(testFoodA);
        assertEquals(1, testList.size());
        assertTrue(testList.contains(testFoodA));

        testList.addFood(testFoodB);
        assertEquals(2, testList.size());
        assertTrue(testList.contains(testFoodA));
        assertTrue(testList.contains(testFoodB));

        testList.addFood(testFoodC);
        testList.addFood(testFoodC);
        testList.addFood(testFoodC);
        assertEquals(5, testList.size());
        assertTrue(testList.contains(testFoodA));
        assertTrue(testList.contains(testFoodB));
        assertTrue(testList.contains(testFoodC));

        assertEquals(5, testList.getFoodList().size());
    }

    @Test
    public void testRemoveFood() {
        assertFalse(testList.contains(testFoodA));
        assertFalse(testList.contains(testFoodB));
        assertFalse(testList.contains(testFoodC));

        testList.addFood(testFoodA);
        testList.addFood(testFoodB);
        assertEquals(2, testList.size());

        testList.removeFood(testFoodA);
        assertFalse(testList.contains(testFoodA));
        assertEquals(1, testList.size());

        testList.removeFood(testFoodC);
        assertEquals(1, testList.size());

        testList.removeFood(testFoodB);
        assertEquals(0, testList.size());
    }

    @Test
    public void testGetTotalPrice() {
        assertEquals(0, testList.getTotalPrice());

        testList.addFood(testFoodA);
        assertEquals(6, testList.getTotalPrice());

        testList.addFood(testFoodC);
        assertEquals(14, testList.getTotalPrice());
    }

    @Test
    public void testGetTotalFoodNum() {
        assertEquals(0, testList.getTotalFoodNum());

        testList.addFood(testFoodA);
        assertEquals(1, testList.getTotalFoodNum());

        testList.addFood(testFoodB);
        testList.addFood(testFoodC);
        assertEquals(3, testList.getTotalFoodNum());
    }

    @Test
    public void testSetDeliveredTime() {
        String time0 = "00:00";
        String time1 = "17:45";
        String invalidTime1 = "1234:56789";
        String invalidTime2 = "NoNoNoNo";
        String invalidTime3 = "1235";

        assertEquals(time0, testList.getDeliveredTime());

        testList.setDeliveredTime(time0);
        assertEquals(time0, testList.getDeliveredTime());

        testList.setDeliveredTime(time1);
        assertEquals(time1, testList.getDeliveredTime());

        testList.setDeliveredTime(invalidTime1);
        assertEquals(time0, testList.getDeliveredTime());

        testList.setDeliveredTime(invalidTime2);
        assertEquals(time0, testList.getDeliveredTime());

        testList.setDeliveredTime(invalidTime3);
        assertEquals(time0, testList.getDeliveredTime());
    }

    @Test
    public void testIsValidTime() {
        String time0 = "00:00";
        assertTrue(testList.isValidTime(time0));

        String time1 = "13:46";
        assertTrue(testList.isValidTime(time1));

        String time2 = "23:59";
        assertTrue(testList.isValidTime(time2));

        String invalidTime1 = "24:00";
        assertFalse(testList.isValidTime(invalidTime1));

        String invalidTime2 = "YesYesYes...";
        assertFalse(testList.isValidTime(invalidTime2));

        String invalidTime3 = "13.46";
        assertFalse(testList.isValidTime(invalidTime3));

        String invalidTime4 = "10:69";
        assertFalse(testList.isValidTime(invalidTime4));

        String invalidTime5 = "9:10";
        assertFalse(testList.isValidTime(invalidTime5));

        String invalidTime6 = "09:1";
        assertFalse(testList.isValidTime(invalidTime6));

    }

    @Test
    public void testSetTotalFoodNum() {
        assertEquals(0, testList.setTotalFoodNum(0));
        assertEquals(5, testList.setTotalFoodNum(5));
    }

    @Test
    public void testSetTotalPrice() {
        assertEquals(0, testList.setTotalPrice(0));
        assertEquals(25, testList.setTotalPrice(25));
    }
}

