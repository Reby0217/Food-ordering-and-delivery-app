package ui;

import model.FoodToOrderList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the visual setting time user interface
public class SetTimeUI extends JFrame implements ActionListener {
    private FoodToOrderList ftoList;
    private FoodToOrderListUI foodToOrderListUI;

    //EFFECTS: sets up the setting delivered time window
    public SetTimeUI(FoodToOrderListUI foodToOrderListUI, FoodToOrderList ftoList) {
        super("Set Delivered Time Window");
        this.ftoList = ftoList;
        this.foodToOrderListUI = foodToOrderListUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
