package ui;

import java.io.FileNotFoundException;

public class MainUI {
    public static void main(String[] args) {
        try {
            new OrderFoodAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }


    }
}
