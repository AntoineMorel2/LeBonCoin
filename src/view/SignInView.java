package view;

import DAO.UserDAO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import hibernate.UserEntity;

import javax.swing.*;
import java.awt.*;

public class SignInView extends JFrame {
    public JPanel accueil;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton connexion;
    private JButton inscrivezVousButton;
    private JLabel img;
    private ImageIcon logo;

    private static SignUpView signUpView;
    //    private static AnnoncesView annoncesView;
    private static CreateAnnonce createAnnonce;

    public SignInView() {
        add(accueil);

        setTitle("Connexion");
        setPreferredSize(new Dimension(500, 500));
        addLogo();

        connexion.addActionListener(actionEvent -> {
            System.out.println("Connection click");
            UserDAO userDAO = new UserDAO();
            String mail = textField1.getText();
            String password = Base64.encode((new String(passwordField1.getPassword())).getBytes());
            UserEntity user = userDAO.checkConnection(mail, password);
            if (user != null) {
                System.out.println("l'utilisateur " + user.getMail() + " est connecté");
                createAnnonce = new CreateAnnonce();
                createAnnonce.pack();
                createAnnonce.setVisible(true);
            } else {
                JOptionPane.showInternalMessageDialog(accueil, "Votre login et votre mot de passe ne correspondent pas.");
            }

        });

        inscrivezVousButton.addActionListener(actionEvent -> {
            //JOptionPane.showMessageDialog(accueil, "aahajgkdsg");
            System.out.println("click");
            signUpView = new SignUpView();
            signUpView.setContentPane(signUpView.signUpPanel);
            //signUpForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signUpView.pack();
            signUpView.setVisible(true);

        });

    }

    /**
     * AJout + redimensionnement de l'image
     */
    public void addLogo() {
        logo = new ImageIcon(getClass().getResource("../ressources/logo.png")); // load the image to a imageIcon
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 30, Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);  // transform it back

        img.setIcon(logo);
        //img.setPreferredSize(new Dimension(120,30));
        accueil.add(img);
    }

}
