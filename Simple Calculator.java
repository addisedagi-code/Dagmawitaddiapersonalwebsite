import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Cs extends JFrame implements ActionListener {

    JLabel flabel = new JLabel("fname");
    JLabel llabel = new JLabel("lname");
    JLabel rlabel = new JLabel("result");

    JPanel panel = new JPanel();

    JTextField ftext = new JTextField(10);
    JTextField stext = new JTextField(10);
    JTextField rtext = new JTextField(10);

    JButton add = new JButton("+");
    JButton sub = new JButton("-");
    JButton mul = new JButton("*");
    JButton div = new JButton("/");
    JButton equal = new JButton("=");

    JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;

    boolean enteringFirst = true;
    String operator = "";

    Cs() {
        setTitle("Calculator app");

        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        panel.add(flabel);
        panel.add(ftext);
        panel.add(llabel);
        panel.add(stext);
        panel.add(rlabel);
        panel.add(rtext);

        panel.add(add);
        panel.add(sub);
        panel.add(mul);
        panel.add(div);
        panel.add(equal);

        panel.add(b0);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(300, 100, 1100, 700);
        add(panel);

        // Action listeners
        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        equal.addActionListener(this);

        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);

        // Focus listeners to know which text field is active
        ftext.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { enteringFirst = true; }
            public void focusLost(FocusEvent e) {}
        });

        stext.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { enteringFirst = false; }
            public void focusLost(FocusEvent e) {}
        });

        setVisible(true);
        setSize(400, 300);
        setResizable(false);
    }

    public static void main(String[] args) {
        new Cs();
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        // Number buttons — append to the active field
        if (src == b0) addNumber("0");
        if (src == b1) addNumber("1");
        if (src == b2) addNumber("2");
        if (src == b3) addNumber("3");
        if (src == b4) addNumber("4");
        if (src == b5) addNumber("5");
        if (src == b6) addNumber("6");
        if (src == b7) addNumber("7");
        if (src == b8) addNumber("8");
        if (src == b9) addNumber("9");

        // Operators
        if (src == add) operator = "+";
        if (src == sub) operator = "-";
        if (src == mul) operator = "*";
        if (src == div) operator = "/";

        // Equal button
        if (src == equal) {
            try {
                double num1 = Double.parseDouble(ftext.getText());
                double num2 = Double.parseDouble(stext.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num2 != 0 ? num1 / num2 : Double.NaN; break;
                }
                rtext.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                rtext.setText("Error");
            }
        }
    }

    private void addNumber(String num) {
        if (enteringFirst) {
            ftext.setText(ftext.getText() + num);
        } else {
            stext.setText(stext.getText() + num);
        }
    }
}