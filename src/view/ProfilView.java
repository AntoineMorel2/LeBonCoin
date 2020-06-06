package view;

import DAO.AnnonceDAO;
import DAO.ImageDAO;
import hibernate.AnnonceEntity;
import hibernate.ImageEntity;
import hibernate.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilView extends JFrame {
    private JPanel jp_profil;
    private JLabel lb_nom;
    private JLabel lb_prenom;
    private JLabel lb_mail;
    private JButton déconnexionButton;
    private JScrollPane jscrollPane;
    private JPanel panelAnonces;
    private JButton retourButton;

    public ProfilView(UserEntity userConnected){
        add(jp_profil);
        setTitle("Fenêtre du profil");
        setPreferredSize(new Dimension(1500, 800));

        AnnonceDAO annonceDAO = new AnnonceDAO();
        ImageDAO imageDAO = new ImageDAO();

        // Remplir les labels liés à l'utilisateurs
        lb_nom.setText(userConnected.getNom());
        lb_prenom.setText(userConnected.getPrenom());
        lb_mail.setText(userConnected.getMail());

        // Remplir la liste des annonces postées par l'utilisateur
        fillList(annonceDAO.fetchFromUser(userConnected.getIdUser()), imageDAO, userConnected);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        déconnexionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void fillList(java.util.List<AnnonceEntity> annonceEntities, ImageDAO imageDAO, UserEntity userConnected) {
        panelAnonces.setLayout(new BoxLayout(panelAnonces, BoxLayout.Y_AXIS));
        for (AnnonceEntity annonce : annonceEntities) {
            ImageEntity image = imageDAO.fetchbyAnnonceId(annonce.getIdAnnonce());
            String path = "src/ressources/" + image.getName() + ".jpg";
            AnnonceItemView annonceView = new AnnonceItemView(userConnected, path, annonce.getTitle(),
                    Float.toString(annonce.getPrice()), annonce.getDescription(), annonce);
            //panelAnonces.setLayout(new BoxLayout(panelAnonces, BoxLayout.Y_AXIS));
            panelAnonces.add(annonceView);
        }
        jscrollPane.updateUI();
    }
}
