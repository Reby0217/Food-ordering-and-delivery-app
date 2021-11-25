package ui;

import model.Food;
import model.FoodToOrderList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the visual adding food user interface
public class AddFoodGUI extends JFrame implements ActionListener {
    private FoodToOrderListGUI foodToOrderListGUI;
    private FoodToOrderList ftoList;
    private JLabel label;
    private JLabel backgroundLabel;
    private static final int X_POSITION = 200;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 50;
    private final String addSaladButtonText = "Salad ($11)";
    private final String addOnionRingsButtonText = "Onion Rings ($6)";
    private final String addPokeBowlButtonText = "Poke Bowl ($15)";
    private final String addBurgerButtonText = "Burger ($13)";
    private final String addCokeButtonText = "Coke ($2)";
    private final String addAppleJuiceButtonText = "Apple Juice ($3)";


    //EFFECTS: sets up the add-food window
    public AddFoodGUI(FoodToOrderListGUI foodToOrderListGUI, FoodToOrderList ftoList) {
        super("Add Food Window");
        this.ftoList = ftoList;
        this.foodToOrderListGUI = foodToOrderListGUI;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(618, 684));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);


        label = new JLabel("Please add the food you want", JLabel.CENTER);
        label.setBounds(110, 120, 400, 25);
        add(label);
        label.setForeground(Color.DARK_GRAY);
        label.setFont(new Font("SansSerif", Font.PLAIN, 22));

        addButtons();
        setBackgroundImage();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: adds buttons on the add-food window
    private void addButtons() {
        setButton(addSaladButtonText, 170);
        setButton(addOnionRingsButtonText, 220);
        setButton(addPokeBowlButtonText, 270);
        setButton(addBurgerButtonText, 320);
        setButton(addCokeButtonText, 370);
        setButton(addAppleJuiceButtonText, 420);

    }

    //EFFECTS: adds a button at the given y-position with set
    //         font, foreground, action command, and action listener of
    private void setButton(String buttonText, int y) {
        JButton button = new JButton(buttonText);
        button.setBounds(X_POSITION, y, WIDTH, HEIGHT);
        add(button);
        button.setFont(new Font("Monospaced", Font.PLAIN, 17));
        button.setForeground(Color.BLACK);
        button.setActionCommand(buttonText);
        button.addActionListener(this);
    }

    //EFFECTS: sets the background image of the add-food window
    private void setBackgroundImage() {
        ImageIcon imageIcon = new ImageIcon("./images/background2.png");
        backgroundLabel = new JLabel("", imageIcon, JLabel.CENTER);
        backgroundLabel.setBounds(0, 0, 618, 684);
        add(backgroundLabel);
    }

    //EFFECTS: acts correspondingly when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        Food salad = new Food("Salad", 11);
        Food onionRings = new Food("Onion Rings", 6);
        Food pokeBowl = new Food("Poke Bowl", 15);
        Food burger = new Food("Burger", 13);
        Food coke = new Food("Coke", 2);
        Food appleJuice = new Food("Apple Juice", 3);
        if (e.getActionCommand().equals(addSaladButtonText)) {
            ftoList.addFood(salad);
        } else if (e.getActionCommand().equals(addOnionRingsButtonText)) {
            ftoList.addFood(onionRings);
        } else if (e.getActionCommand().equals(addPokeBowlButtonText)) {
            ftoList.addFood(pokeBowl);
        } else if (e.getActionCommand().equals(addBurgerButtonText)) {
            ftoList.addFood(burger);
        } else if (e.getActionCommand().equals(addCokeButtonText)) {
            ftoList.addFood(coke);
        } else if (e.getActionCommand().equals(addAppleJuiceButtonText)) {
            ftoList.addFood(appleJuice);
        }

        JOptionPane.showMessageDialog(null, "Add Food Successfully");
        foodToOrderListGUI.dispose();
        foodToOrderListGUI = new FoodToOrderListGUI(ftoList);
        dispose();
    }
}
