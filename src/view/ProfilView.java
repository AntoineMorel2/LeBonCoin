package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilView extends JFrame {
    private JPanel jp_profil;
    private JLabel lb_nom;
    private JLabel lb_prenom;
    private JLabel lb_mail;
    private JList liste_annonces;
    private JButton déconnexionButton;

    public ProfilView(){
        add(jp_profil);
        setTitle("Fenêtre du profil");
        setPreferredSize(new Dimension(500, 500));
        déconnexionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //code pour gérer la déconnexion et renvoyer sur la fenêtre de connexion
            }
        });
    }
}
