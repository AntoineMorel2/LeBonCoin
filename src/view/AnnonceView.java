package view;

import javax.swing.*;
import java.awt.*;

public class AnnonceView extends JPanel {
    private ImageIcon logo;
    public JPanel annonceView;
    private JLabel titreAnnonce;
    private JLabel prix;
    private JLabel description;
    private JPanel imagePanel;

    public AnnonceView() {
        add(annonceView);

        setPreferredSize(new Dimension(500, 200));
        addLogo();
    }

    public void addLogo() {
        /**
         * AJout + redimensionnement de l'image
         */
        logo = new ImageIcon(getClass().getResource("../ressources/testAnnonce.png")); // load the image to a imageIcon
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);  // transform it back

        JLabel labelImg = new JLabel(logo);

        imagePanel.add(labelImg);
        imagePanel.updateUI();
    }
}
