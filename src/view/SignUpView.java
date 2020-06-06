package view;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends JFrame{

    public JPanel signUpPanel;

    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton inscriptionButton;

    private AnnonceItemView annonceItemView = new AnnonceItemView();

    public SignUpView() {
        add(signUpPanel);

        setTitle("Inscription");
        setPreferredSize(new Dimension(500, 500));
    }
}
