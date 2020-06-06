package view;

import DAO.AnnonceDAO;
import DAO.ImageDAO;
import hibernate.AnnonceEntity;
import hibernate.ImageEntity;
import hibernate.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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


    public AnnoncesView(UserEntity userConnected) {
        add(annoncesPanel);
        setTitle("LeCoinBon");
        setPreferredSize(new Dimension(1500, 600));
        panelAnnonces.setMinimumSize(new Dimension(1000, 800));
        panelAnnonces.setPreferredSize(new Dimension(1000, 9999));
        scrollAnnonces.setPreferredSize(new Dimension(1000, 800));

        // redefinir le layout permet de placer les éléments soient les uns en dessous des autres
        panelAnnonces.setLayout(new BoxLayout(panelAnnonces, BoxLayout.Y_AXIS));

        AnnonceDAO annonceDAO = new AnnonceDAO();
        ImageDAO imageDAO = new ImageDAO();
        fillList(annonceDAO, imageDAO, userConnected);
        addLogo();

        posterUneAnnonceButton.addActionListener(actionEvent -> {
            CreateAnnonce annonceCreation = new CreateAnnonce(userConnected);
            annonceCreation.pack();
            annonceCreation.setAlwaysOnTop(true);
            annonceCreation.setVisible(true);
            annonceCreation.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent windowEvent) {
                    //
                }

                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    //

                }

                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    fillList(annonceDAO, imageDAO, userConnected);
                }

                @Override
                public void windowIconified(WindowEvent windowEvent) {
                    //

                }

                @Override
                public void windowDeiconified(WindowEvent windowEvent) {
                    //

                }

                @Override
                public void windowActivated(WindowEvent windowEvent) {
                    //

                }

                @Override
                public void windowDeactivated(WindowEvent windowEvent) {
                    //

                }
            });
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

        JLabel labelImg = new JLabel(logo);

        imagePanel.add(labelImg);
        imagePanel.updateUI();
    }

    private void fillList(AnnonceDAO annonceDAO, ImageDAO imageDAO, UserEntity userConnected) {
        java.util.List<AnnonceEntity> annonceEntities = annonceDAO.fetchAll();
        panelAnnonces.removeAll();
        for (AnnonceEntity annonce : annonceEntities) {
            ImageEntity image = imageDAO.fetchbyAnnonceId(annonce.getIdAnnonce());
            String path = "src/ressources/" + image.getName() + ".jpg";
            AnnonceItemView annonceView = new AnnonceItemView(userConnected, path, annonce.getTitle(),
                    Float.toString(annonce.getPrice()), annonce.getDescription(), annonce);
            panelAnnonces.add(annonceView);

        }
        annoncesPanel.updateUI();
    }
}
