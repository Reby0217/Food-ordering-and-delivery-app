package ui;

import model.Food;
import model.FoodToOrderList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents visual user interface for food-to-order list (including list of food,
// total price, total number of food and delivered time)
public class FoodToOrderListUI extends JFrame implements ActionListener {
    private FoodToOrderList ftoList;
    private DefaultTableModel tableModel;
    private JTable table;
    private String addFoodButton = "Add Food";
    private String setTimeButton = "Set Delivered Time";


    //EFFECTS: sets up the food-to-order list window
    public FoodToOrderListUI(FoodToOrderList ftoList) {

        this.ftoList = ftoList;

        setPreferredSize(new Dimension(650, 650));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        setFoodToOrderListTable(ftoList);

        add(new JScrollPane(table));

        addButtons();
        displayOrderSummary();

        setTitle("Food-to-order List");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    //EFFECTS: displays the table of the list of food including
    //         index, name, and price of each food
    private void setFoodToOrderListTable(FoodToOrderList ftoList) {
        createEmptyFoodToOrderListTable();

        int index = 1;
        for (int i = 0; i < ftoList.size(); i++) {
            Food food = ftoList.getFoodList().get(i);
            Object[] row = new Object[] {
                    index + 1,
                    food.getName(),
                    food.getPrice()
            };
            tableModel.addRow(row);
        }
    }


    //EFFECTS: creates an empty food-to-order list table with column names
    private void createEmptyFoodToOrderListTable() {
        final String[] columnNames = new String[]{
                "Index", "Name", "Price"
        };

        tableModel = new DefaultTableModel(null, columnNames) {
        };
        table = new JTable(tableModel);
    }

    //EFFECTS: add buttons on the main window
    private void addButtons() {
        setButton(addFoodButton);
        setButton(setTimeButton);
    }

    //EFFECTS: sets the font, foreground, action command, and action listener of a button
    private void setButton(String str) {
        JButton button = new JButton(str);
        button.setFont(new Font("Arial", Font.PLAIN, 17));
        add(button);
        button.setForeground(Color.BLACK);
        button.setActionCommand(str);
        button.addActionListener(this);
    }

    //EFFECTS: displays the order summary
    private void displayOrderSummary() {
        showTotalNum();
        showTotalPrice();
        showDeliveredTime();
    }

    //EFFECTS: shows the total number of food in the list
    private void showTotalNum() {
        int totalNumber = ftoList.getTotalFoodNum();
        JLabel totalNumLabel = new JLabel("Total number of food: " + totalNumber + "\n");
        totalNumLabel.setBounds(500,500,500,20);
        add(totalNumLabel);
    }

    //EFFECTS: shows the total price of all the food in the list
    private void showTotalPrice() {
        int totalPrice = ftoList.getTotalPrice();
        JLabel totalPriceLabel = new JLabel("Total price of food: $" + totalPrice + "\n");
        totalPriceLabel.setBounds(500,600,400,20);
        add(totalPriceLabel);
    }

    //EFFECTS: shows the delivered time of the order
    private void showDeliveredTime() {
        String deliveredTime = ftoList.getDeliveredTime();
        JLabel deliveredTimeLabel = new JLabel("Delivered time in 24-hour clock: " + deliveredTime + "\n");
        deliveredTimeLabel.setBounds(500,700,500,20);
        add(deliveredTimeLabel);
    }



    @Override
    public void actionPerformed(ActionEvent e) {


        dispose();
        new FoodToOrderListUI(ftoList);

    }
}
