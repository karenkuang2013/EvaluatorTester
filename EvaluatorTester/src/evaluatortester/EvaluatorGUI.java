/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluatortester;

/**
 *
 * @author qihongkuang
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author qihongkuang
 */
public class EvaluatorGUI extends JFrame implements ActionListener {

    Evaluator calculator = new Evaluator();

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();
    private Button buttons[] = new Button[20]; //total 20 buttons on the calculator, numbered from left to right, up to down
//bText[] array contains text on corresponding buttons
    private static final String bText[] = {"(", ")", "C", "CE", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "=", "^", "/"};

    /**
     *
     * @param argv
     */
    public static void main(String argv[]) {
        EvaluatorGUI calc = new EvaluatorGUI();
    }
    
    /**
     *
     */
    public EvaluatorGUI() {
        setLayout(new BorderLayout());

        add(txField, BorderLayout.NORTH);

        txField.setEditable(false);  
        
        add(buttonPanel, BorderLayout.CENTER);

        buttonPanel.setLayout(new GridLayout(5, 4));

        for (int i = 0; i < 20; i++) //create 20 buttons with corresponding text in bText[] array
        {
            buttons[i] = new Button(bText[i]);
        }
        for (int i = 0; i < 20; i++) //add buttons to button panel
        {
            buttonPanel.add(buttons[i]);
        }
        for (int i = 0; i < 20; i++) //set up buttons to listen for mouse input
        {
            buttons[i].addActionListener(this);
        }
        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        buttons[17].setForeground(Color.BLUE);
        

    }

    public void actionPerformed(ActionEvent arg0) { // You need to fill in this fuction
        String input = txField.getText();

        if (arg0.getSource() == buttons[0]) {

            txField.setText(txField.getText() + "(");

        } else if (arg0.getSource() == buttons[1]) {

            txField.setText(txField.getText() + ")");

        } else if (arg0.getSource() == buttons[2]) {
    
            input = input.substring(0,input.length()-1);
            txField.setText(input);

        } else if (arg0.getSource() == buttons[3]) {

            txField.setText("");

        } else if (arg0.getSource() == buttons[4]) {

            txField.setText(txField.getText() + "7");

        } else if (arg0.getSource() == buttons[5]) {

            txField.setText(txField.getText() + "8");

        } else if (arg0.getSource() == buttons[6]) {

            txField.setText(txField.getText() + "9");

        } else if (arg0.getSource() == buttons[7]) {

            txField.setText(txField.getText() + "+");

        } else if (arg0.getSource() == buttons[8]) {

            txField.setText(txField.getText() + "4");

        } else if (arg0.getSource() == buttons[9]) {

            txField.setText(txField.getText() + "5");

        } else if (arg0.getSource() == buttons[10]) {

            txField.setText(txField.getText() + "6");

        } else if (arg0.getSource() == buttons[11]) {

            txField.setText(txField.getText() + "-");

        } else if (arg0.getSource() == buttons[12]) {

            txField.setText(txField.getText() + "1");

        } else if (arg0.getSource() == buttons[13]) {

            txField.setText(txField.getText() + "2");

        } else if (arg0.getSource() == buttons[14]) {

            txField.setText(txField.getText() + "3");

        } else if (arg0.getSource() == buttons[15]) {

            txField.setText(txField.getText() + "*");

        } else if (arg0.getSource() == buttons[16]) {

            txField.setText(txField.getText() + "0");

        } else if (arg0.getSource() == buttons[17]) {

            input = txField.getText();
            txField.setText(Integer.toString(calculator.eval(input)));

        } else if (arg0.getSource() == buttons[18]) {

            txField.setText(txField.getText() + "^");

        } else if (arg0.getSource() == buttons[19]) {

            txField.setText(txField.getText() + "/");

        }

    }
}
