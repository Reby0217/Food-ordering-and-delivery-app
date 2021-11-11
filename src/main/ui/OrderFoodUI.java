package ui;

import model.FoodToOrderList;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents the application's main window frame
public class OrderFoodUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/foodToOrderList.json";
    private FoodToOrderList ftoList;
    private static final int POSITION = 90;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 30;
    private JButton orderButton;
    private JButton quitButton;
    private JButton saveButton;
    private JButton loadButton;
    private FoodListUI foodListUI;
    private ImageIcon imageIcon;
    private JLabel selectionLabel;
    private JLabel backgroundLabel;
    private JsonReader jsonReader;


    // This method references code from this website
    // Link: https://stackoverflow.com/a/6578266
    //EFFECTS: sets up the order food window
    public OrderFoodUI() {
        super("Order Food Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(450, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        init();

    }

    //MODIFIES: this
    //EFFECTS: initializes foodToOrderList, buttons, labels, and image icon
    private void init() {
        ftoList = new FoodToOrderList();
        orderButton = new JButton("Order your food");
        quitButton = new JButton("Quit the APP");
        saveButton = new JButton("Save your order");
        loadButton = new JButton("Load your saved order");
        selectionLabel = new JLabel("Please select from the following choices", JLabel.CENTER);
        imageIcon = new ImageIcon("./images/background1.jpeg");
        backgroundLabel = new JLabel("", imageIcon, JLabel.CENTER);
    }


    //MODIFIES: this
    //EFFECTS: loads food-to-order list from file
    private void loadFoodToOrderList() {
        try {
            ftoList = jsonReader.read();
            System.out.println("Loaded the food-to-order list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    // starts the APP
    public static void main(String[] args) {
        new OrderFoodUI();
    }
}
