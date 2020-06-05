package view;

import DAO.UserDAO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import hibernate.UserEntity;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends JFrame{

    public JPanel signUpPanel;

    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton inscriptionButton;
    private JTextField nomUtilisateur;
    private JTextField prenomUtilisateur;

    public SignUpView() {
        add(signUpPanel);

        setTitle("Inscription");
        setPreferredSize(new Dimension(500, 500));

        inscriptionButton.addActionListener(actionEvent -> {
            String password1 = Base64.encode((new String(passwordField1.getPassword())).getBytes());
            String password2 = Base64.encode((new String(passwordField2.getPassword())).getBytes());
            if (!password1.equals(password2)) {
                JOptionPane.showMessageDialog(signUpPanel, "Vos mots de passe ne correspondent pas, veuillez changer.");
            } else {
                if (!textField1.getText().equals(textField2.getText())) {
                    JOptionPane.showMessageDialog(signUpPanel, "Vos emails ne correspondent pas, veuillez changer.");
                } else {
                    try {
                        UserEntity newUser = new UserEntity(nomUtilisateur.getText(), prenomUtilisateur.getText(), textField1.getText(), password1);
                        UserDAO uDao = new UserDAO();
                        if (uDao.create(newUser)) {
                            JOptionPane.showMessageDialog(signUpPanel, "L'utilisateur avec le prénom " + prenomUtilisateur.getText() + " a bien été ajouté");
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(signUpPanel, "Impossible d'ajouter l'utilisateur, veuillez réessayer");
                        }
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(signUpPanel, "Une erreur est apparue lors de l'ajout de l'utilisateur vérifier le bon format de l'email");
                    }

                }
            }
        });
    }
}
