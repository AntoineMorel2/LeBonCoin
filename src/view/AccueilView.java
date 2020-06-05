package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AccueilView extends JFrame {
    public JPanel accueil;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton connexion;
    private JButton inscrivezVousButton;
    private JLabel img;
    private ImageIcon logo;

    public AccueilView() throws IOException {
        add(accueil);

        setTitle("test");
        setPreferredSize(new Dimension(500, 500));
        addLogo();

        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(accueil, "aahajgkdsg");
            }
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
