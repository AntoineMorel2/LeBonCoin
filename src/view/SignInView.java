package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignInView extends JFrame {
    public JPanel accueil;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton connexion;
    private JButton inscrivezVousButton;
    private JLabel img;
    private ImageIcon logo;

    private static SignUpForm signUpForm;

    public SignInView() {
        add(accueil);

        setTitle("Connexion");
        setPreferredSize(new Dimension(500, 500));
        addLogo();

        inscrivezVousButton.addActionListener(actionEvent -> {
            //JOptionPane.showMessageDialog(accueil, "aahajgkdsg");
            System.out.println("click");
            signUpForm = new SignUpForm();
            signUpForm.setContentPane(signUpForm.signUpPanel);
            //signUpForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signUpForm.pack();
            signUpForm.setVisible(true);

        });

    }

    public void addLogo() {
        /**
         * AJout + redimensionnement de l'image
         */
        logo = new ImageIcon(getClass().getResource("../ressources/logo.png")); // load the image to a imageIcon
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 30, Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);  // transform it back

        img.setIcon(logo);
        //img.setPreferredSize(new Dimension(120,30));
        accueil.add(img);
    }

}
