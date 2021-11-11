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
    private static final int XPOSITION = 540;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 50;
    private JButton orderButton;
    private JButton quitButton;
    private JButton saveButton;
    private JButton loadButton;
    private FoodListUI foodListUI;
    private ImageIcon backgroundImageIcon;
    private JLabel selectionLabel;
    private JLabel backgroundLabel;


    // This method references code from this website
    // Link: https://stackoverflow.com/a/6578266
    //EFFECTS: sets up the order food window
    public OrderFoodUI() {
        super("Order Food Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(847, 960));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        init();

        selectionLabel.setBounds(470, 20, 410, 100);
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
    //EFFECTS: initializes foodToOrderList, buttons, labels, button panel and image icon
    private void init() {
        ftoList = new FoodToOrderList();
        orderButton = new JButton("Start your order");
        saveButton = new JButton("Save your order");
        loadButton = new JButton("Load your saved order");
        quitButton = new JButton("Quit the APP");
        selectionLabel = new JLabel("Please select from: ", JLabel.CENTER);
        backgroundImageIcon = new ImageIcon("./images/background1.jpeg");
        backgroundLabel = new JLabel("", backgroundImageIcon, JLabel.CENTER);

    }


    //EFFECTS: add buttons on the main window
    private void addButtons() {
        setButton(orderButton, 100);
        setButton(saveButton, 160);
        setButton(loadButton, 220);
        setButton(quitButton, 280);

    }

    //EFFECTS: sets a button at given y-position
    private void setButton(JButton button, int yposition) {
        button.setBounds(XPOSITION, yposition, WIDTH, HEIGHT);
        button.setFont(new Font("Arial", Font.PLAIN, 17));
        add(button);
        button.setActionCommand(button.getName());
        button.addActionListener(this);
        button.setForeground(Color.BLACK);
    }

    //EFFECTS: set the background image of the main window
    private void setBackgroundImage() {
        backgroundLabel.setBounds(0, 0, 847, 960);
        add(backgroundLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    // starts the APP
    public static void main(String[] args) {
        new OrderFoodUI();
    }
}
