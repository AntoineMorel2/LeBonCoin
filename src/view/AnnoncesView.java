package view;

import DAO.AnnonceDAO;
import DAO.CategoryDAO;
import DAO.ImageDAO;
import hibernate.AnnonceEntity;
import hibernate.CategoryEntity;
import hibernate.ImageEntity;
import hibernate.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class AnnoncesView extends JFrame{
    private JTextField textFieldRecherche;
    private JButton rechercherButton;
    public JPanel annoncesPanel;
    private JPanel panelAnnonces;
    private JScrollPane scrollAnnonces;
    private JButton posterUneAnnonceButton;
    private JButton monProfilButton;
    private JPanel imagePanel;
    private JComboBox comboBoxCategories;
    private JButton rechercheButton;

    private static ProfilView profilView;

    private ImageIcon logo;

    // pour les tests : classe vide à remplir avec les infos


    public AnnoncesView(UserEntity userConnected) {
        add(annoncesPanel);
        setTitle("LeCoinBon");
        setPreferredSize(new Dimension(1500, 800));
        panelAnnonces.setLayout(new BoxLayout(panelAnnonces, BoxLayout.Y_AXIS));

        // redefinir le layout permet de placer les éléments soient les uns en dessous des autres

        AnnonceDAO annonceDAO = new AnnonceDAO();
        ImageDAO imageDAO = new ImageDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        fillList(annonceDAO.fetchAll(), imageDAO, userConnected);
        addLogo();

        // Remplissage JComboBox
        List<CategoryEntity> categoryEntityList = categoryDAO.fetchAll();
        for(CategoryEntity categoryEntity : categoryEntityList) {
            comboBoxCategories.addItem(categoryEntity.getName());
        }
        //Recherche par cate
        rechercheButton.addActionListener(actionEvent -> {
            int categoryId = categoryDAO.fetchByName((String) comboBoxCategories.getSelectedItem()).get(0).getIdCategory();
            java.util.List<AnnonceEntity> annonceEntities = annonceDAO.fetchByCategory(categoryId);
            fillList(annonceEntities, imageDAO, userConnected);
        });

        //Recherche par nom
        rechercherButton.addActionListener(actionEvent -> {
            java.util.List<AnnonceEntity> annonceEntities = annonceDAO.fetchByName(textFieldRecherche.getText());
            fillList(annonceEntities, imageDAO, userConnected);
        });

        monProfilButton.addActionListener(actionEvent -> {
            if (userConnected != null) {
                profilView = new ProfilView(userConnected);
                profilView.pack();
                profilView.setVisible(true);
            } else {
                JOptionPane.showInternalMessageDialog(annoncesPanel, "Erreur d'accès à votre compte");
            }
        });

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
                    fillList(annonceDAO.fetchAll(), imageDAO, userConnected);
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

    private void fillList(java.util.List<AnnonceEntity> annonceEntities, ImageDAO imageDAO, UserEntity userConnected) {
        panelAnnonces.removeAll();
        for (AnnonceEntity annonce : annonceEntities) {
            ImageEntity image = imageDAO.fetchbyAnnonceId(annonce.getIdAnnonce());
            String path = "src/ressources/" + image.getName() + ".jpg";
            AnnonceItemView annonceView = new AnnonceItemView(userConnected, path, annonce.getTitle(),
                    Float.toString(annonce.getPrice()), annonce.getDescription(), annonce);
            panelAnnonces.add(annonceView);
        }
        scrollAnnonces.updateUI();
    }
}
