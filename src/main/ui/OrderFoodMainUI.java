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
public class OrderFoodMainUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/foodToOrderList.json";
    private FoodToOrderList ftoList;
    private static final int X_POSITION = 390;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 50;
    private String orderButton;
    private String quitButton;
    private String saveButton;
    private String loadButton;
    private FoodToOrderListUI foodToOrderListUI;
    private ImageIcon backgroundImageIcon;
    private JLabel selectionLabel;
    private JLabel backgroundLabel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // This method references code from this website
    // Link: https://stackoverflow.com/a/6578266
    //EFFECTS: sets up the order-food main window
    public OrderFoodMainUI() throws FileNotFoundException {
        super("Order Food Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(702, 856));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        init();

        selectionLabel.setBounds(320, 20, 410, 100);
        add(selectionLabel);
        selectionLabel.setForeground(Color.DARK_GRAY);
        selectionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

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
        orderButton = "Start New Order";
        saveButton = "Save Your Order";
        loadButton = "Load Your Saved Order";
        quitButton = "Quit the APP";
        selectionLabel = new JLabel("Please select from: ", JLabel.CENTER);
        backgroundImageIcon = new ImageIcon("./images/background1.png");
        backgroundLabel = new JLabel("", backgroundImageIcon, JLabel.CENTER);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


    }


    //EFFECTS: adds buttons on the order-food main window
    private void addButtons() {
        setButton(loadButton, 110);
        setButton(orderButton, 170);
        setButton(saveButton, 230);
        setButton(quitButton, 290);

    }

    //EFFECTS: sets the font, foreground, action command, and action listener of
    //         a button at the given y-position
    private void setButton(String buttonText, int y) {
        JButton button = new JButton(buttonText);
        button.setBounds(X_POSITION, y, WIDTH, HEIGHT);
        button.setFont(new Font("SansSerif", Font.PLAIN, 17));
        add(button);
        button.setForeground(Color.BLACK);
        button.setActionCommand(buttonText);
        button.addActionListener(this);
    }

    //EFFECTS: sets the background image of the main window
    private void setBackgroundImage() {
        backgroundLabel.setBounds(0, 0, 702, 856);
        add(backgroundLabel);
    }


    //EFFECTS: acts correspondingly when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(quitButton)) {
            System.exit(0);
        } else if (e.getActionCommand().equals(orderButton)) {
            this.ftoList = new FoodToOrderList();
            //set default time to be "00:00"
            ftoList.setDeliveredTime("00:00");
            new FoodToOrderListUI(ftoList);
        } else if (e.getActionCommand().equals(loadButton)) {
            loadFoodToOrderList();
            foodToOrderListUI = new FoodToOrderListUI(ftoList);
        } else if (e.getActionCommand().equals(saveButton)) {
            saveFoodToOrderList();
            JOptionPane.showMessageDialog(null, "Save your current order successfully");
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

    // starts the order-food main ui
    public static void main(String[] args) {
        try {
            new OrderFoodMainUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}





