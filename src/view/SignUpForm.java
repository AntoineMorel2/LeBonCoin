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

    private AnnonceView annonceView = new AnnonceView();

    public SignUpForm() {
        add(signUpPanel);

        setTitle("Inscription");
        setPreferredSize(new Dimension(500, 500));

        //signUpPanel.add(annonceView, new com.intellij.uiDesigner.core.GridConstraints());
    }
}
