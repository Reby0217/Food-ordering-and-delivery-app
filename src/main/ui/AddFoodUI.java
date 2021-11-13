package ui;

import model.FoodToOrderList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the visual adding food user interface
public class AddFoodUI extends JFrame implements ActionListener {
    private FoodToOrderListUI foodToOrderListUI;
    private FoodToOrderList ftoList;
    private JLabel label;
    private static final int POSITION = 80;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 30;


    //EFFECTS: sets up the adding food window
    public AddFoodUI(FoodToOrderListUI foodToOrderListUI, FoodToOrderList ftoList) {
        super("Add Food Window");
        this.ftoList = ftoList;
        this.foodToOrderListUI = foodToOrderListUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
