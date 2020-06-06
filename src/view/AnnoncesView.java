package view;

import javax.swing.*;
import java.awt.*;

public class AnnoncesView extends JFrame{
    private JTextField textFieldRecherche;
    private JButton rechercherButton;
    public JPanel annoncesPanel;
    private JPanel panelAnnonces;
    private JScrollPane scrollAnnonces;
    private JButton posterUneAnnonceButton;
    private JButton monProfilButton;
    private JPanel imagePanel;

    private ImageIcon logo;

    // pour les tests : classe vide à remplir avec les infos


    public AnnoncesView() {
        add(annoncesPanel);
        setTitle("LeCoinBon");
        panelAnnonces.setPreferredSize(new Dimension(1300,800));

        // redefinir le layout permet de placer les éléments soient les uns en dessous des autres
        panelAnnonces.setLayout(new BoxLayout(panelAnnonces,BoxLayout.PAGE_AXIS));

        // pour les tests : 10 items ==> getAllAnnonces();
        for(int i=0; i<10; i++) {
            panelAnnonces.add(new AnnonceItemView());
        }
        addLogo();
    }

    public void addLogo() {
        /**
         * AJout + redimensionnement de l'image
         */
        logo = new ImageIcon(getClass().getResource("../ressources/logo.png")); // load the image to a imageIcon
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 30, Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);  // transform it back

        JLabel labelImg = new JLabel(logo);

        imagePanel.add(labelImg);
        imagePanel.updateUI();
    }
}
