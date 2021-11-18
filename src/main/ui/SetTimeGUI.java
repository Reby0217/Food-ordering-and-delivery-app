package ui;

import model.FoodToOrderList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the visual setting time user interface
public class SetTimeGUI extends JFrame implements ActionListener {
    private FoodToOrderList ftoList;
    private FoodToOrderListGUI foodToOrderListGUI;
    private JTextField timeField;
    private String setTimeButtonText = "Set";

    //EFFECTS: sets up the setting delivered time window
    public SetTimeGUI(FoodToOrderListGUI foodToOrderListGUI, FoodToOrderList ftoList) {
        super("Set Delivered Time Window");
        this.ftoList = ftoList;
        this.foodToOrderListGUI = foodToOrderListGUI;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(750, 280));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JLabel timeLabel = new JLabel("Please set delivered time in XX:XX form (24-hour-clock)");
        timeLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
        timeLabel.setBounds(55, 40, 650, 50);
        add(timeLabel);
        timeLabel.setForeground(Color.DARK_GRAY);

        timeField = new JTextField(30);
        timeField.setBounds(270, 120, 200, 40);
        add(timeField);

        addButton();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: adds a button on the set-time window with set
    //         font, foreground, action command, and action listener of
    private void addButton() {
        JButton finishButton = new JButton(setTimeButtonText);
        finishButton.setBounds(320, 210, 100, 30);
        add(finishButton);
        finishButton.setFont(new Font("Monospaced", Font.PLAIN, 17));
        finishButton.setActionCommand(setTimeButtonText);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.DARK_GRAY);
    }


    //EFFECTS: acts correspondingly when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(setTimeButtonText)) {
            String deliveredTime = timeField.getText();
            if (!ftoList.isValidTime(deliveredTime)) {
                JOptionPane.showMessageDialog(null, "Invalid time....\n");
            } else {
                ftoList.setDeliveredTime(deliveredTime);
            }

            foodToOrderListGUI.dispose();
            new FoodToOrderListGUI(ftoList);
            dispose();
        }
    }
}
