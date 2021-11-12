package ui;

import model.FoodToOrderList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


// Represents the application's main window frame
public class OrderFoodUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/foodToOrderList.json";
    private FoodToOrderList ftoList;
    private static final int XPOSITION = 390;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 50;
    private JButton orderButton;
    private JButton quitButton;
    private JButton saveButton;
    private JButton loadButton;
    private FoodToOrderListUI foodToOrderListUI;
    private ImageIcon backgroundImageIcon;
    private JLabel selectionLabel;
    private JLabel backgroundLabel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // This method references code from this website
    // Link: https://stackoverflow.com/a/6578266
    //EFFECTS: sets up the order food window
    public OrderFoodUI() {
        super("Order Food Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(702, 856));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        init();

        selectionLabel.setBounds(320, 20, 410, 100);
        add(selectionLabel);
        selectionLabel.setForeground(Color.DARK_GRAY);
        selectionLabel.setFont(new Font("Arial", Font.BOLD, 20));

        addButtons();

        setBackgroundImage();

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //MODIFIES: this
    //EFFECTS: initializes foodToOrderList, buttons, labels, button panel,
    //         image icon, json writer, and json reader
    private void init() {
        ftoList = new FoodToOrderList();
        ftoList.setDeliveredTime("00:00");
        orderButton = new JButton("Start New Order");
        saveButton = new JButton("Save Your Order");
        loadButton = new JButton("Load Your Saved Order");
        quitButton = new JButton("Quit the APP");
        selectionLabel = new JLabel("Please select from: ", JLabel.CENTER);
        backgroundImageIcon = new ImageIcon("./images/background1.png");
        backgroundLabel = new JLabel("", backgroundImageIcon, JLabel.CENTER);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


    }


    //EFFECTS: add buttons on the main window
    private void addButtons() {
        setButton(orderButton, 110);
        setButton(saveButton, 170);
        setButton(loadButton, 230);
        setButton(quitButton, 290);

    }

    //EFFECTS: sets the font, foreground, action command, and action listener of
    //         a button at the given y-position
    private void setButton(JButton button, int yposition) {
        button.setBounds(XPOSITION, yposition, WIDTH, HEIGHT);
        button.setFont(new Font("Arial", Font.PLAIN, 17));
        add(button);
        button.setForeground(Color.BLACK);
        button.setActionCommand(button.getText());
        button.addActionListener(this);
    }

    //EFFECTS: set the background image of the main window
    private void setBackgroundImage() {
        backgroundLabel.setBounds(0, 0, 702, 856);
        add(backgroundLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        foodToOrderListUI = new FoodToOrderListUI(ftoList);
        if (e.getActionCommand().equals("Start new order")) {
            new FoodToOrderListUI(ftoList);
        } else if (e.getActionCommand().equals("Load your saved order")) {
            if (foodToOrderListUI != null) {
                foodToOrderListUI.dispose();
            }
            loadFoodToOrderList();
            foodToOrderListUI = new FoodToOrderListUI(ftoList);
        } else if (e.getActionCommand().equals("Save your order")) {
            saveFoodToOrderList();
        }

    }

    //EFFECTS: saves the food-to-order list to file
    private void saveFoodToOrderList() {
        try {
            jsonWriter.open();
            jsonWriter.write(ftoList);
            jsonWriter.close();
            System.out.println("Saved the food-to-order list to " + JSON_STORE + " successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
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

    // starts the APP
    public static void main(String[] args) {
        new OrderFoodUI();
    }
}



