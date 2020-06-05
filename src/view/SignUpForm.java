package view;

import javax.swing.*;
import java.awt.*;

public class SignUpForm extends JFrame{

    public JPanel signUpPanel;

    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton inscriptionButton;

    public SignUpForm() {
        add(signUpPanel);

        setTitle("Inscription");
        setPreferredSize(new Dimension(500, 500));
    }
}
