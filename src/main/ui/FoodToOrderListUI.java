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
    private String removeFoodButton = "Remove Food";
    private String setTimeButton = "Set Delivered Time";


    //EFFECTS: sets up the food-to-order list window
    public FoodToOrderListUI(FoodToOrderList ftoList) {

        this.ftoList = ftoList;

        setPreferredSize(new Dimension(500, 650));
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
        setResizable(false);
    }

    //EFFECTS: displays the table of the list of food including
    //         index, name, and price of each food
    private void setFoodToOrderListTable(FoodToOrderList ftoList) {
        createEmptyFoodToOrderListTable();

        for (int index = 0; index < ftoList.size(); index++) {
            Food food = ftoList.getFoodList().get(index);
            Object[] row = new Object[]{
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
                "Index","Name", "Price ($)"
        };

        tableModel = new DefaultTableModel(null, columnNames) {
        };
        table = new JTable(tableModel);
        table.setRowHeight(22);
        table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
    }

    //EFFECTS: adds buttons on the food-to-order list window
    private void addButtons() {
        setButton(addFoodButton);
        setButton(removeFoodButton);
        setButton(setTimeButton);
    }

    //EFFECTS: sets the foreground, action command, and action listener of a button
    private void setButton(String str) {
        JButton button = new JButton(str);
        add(button);
        button.setForeground(Color.BLACK);
        button.setActionCommand(str);
        button.addActionListener(this);
    }


    // This method references code from this website
    // Link: https://stackoverflow.com/a/12589611
    //EFFECTS: displays the order summary on the food-to-order list window
    private void displayOrderSummary() {
        Box box = Box.createVerticalBox();

        //shows the total number of food in the list
        int totalNumber = ftoList.getTotalFoodNum();
        JLabel totalNumLabel = new JLabel("Total number of food: " + totalNumber + "\n");
        totalNumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(totalNumLabel);

        //shows the total price of all the food in the list
        int totalPrice = ftoList.getTotalPrice();
        JLabel totalPriceLabel = new JLabel("Total price of food: $" + totalPrice + "\n");
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(totalPriceLabel);

        //shows the delivered time of the order
        String deliveredTime = ftoList.getDeliveredTime();
        JLabel deliveredTimeLabel = new JLabel("Delivered time in 24-hour clock: " + deliveredTime + "\n");
        deliveredTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(deliveredTimeLabel);

        add(box);

    }


    //EFFECTS: acts correspondingly when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(addFoodButton)) {
            new AddFoodUI(this, ftoList);
        } else if (e.getActionCommand().equals(setTimeButton)) {
            new SetTimeUI(this, ftoList);
        } else if (e.getActionCommand().equals(removeFoodButton)) {
            deleteSelectedRowFromTable();
        }

    }


    // This method references code from this website
    // Link: https://stackoverflow.com/a/23465377
    //MODIFIES: this
    //EFFECTS: removes selected row from the table
    public void deleteSelectedRowFromTable() {
        int getSelectedRowForDeletion = table.getSelectedRow();
        if (getSelectedRowForDeletion >= 0) {
            tableModel.removeRow(getSelectedRowForDeletion);
            ftoList.removeFood(ftoList.getFoodList().get(getSelectedRowForDeletion));
            dispose();
            new FoodToOrderListUI(ftoList);
            JOptionPane.showMessageDialog(null, "Remove Successfully");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Unable to remove. Please select a food item in the table");
        }
    }
}
