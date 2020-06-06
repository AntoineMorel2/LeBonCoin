package view;

import hibernate.AnnonceEntity;
import hibernate.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AnnonceItemView extends JPanel {
    private ImageIcon logo;
    public JPanel annonceView;
    private JLabel titreAnnonce;
    private JLabel prix;
    private JLabel description;
    private JPanel imagePanel;

    public AnnonceItemView(UserEntity user, String path, String title, String price, String description, AnnonceEntity annonceEntity) {
        add(annonceView);
        titreAnnonce.setText(title);
        prix.setText(price+"€");
        this.description.setText(description);
        setPreferredSize(new Dimension(500, 200));
        addLogo(path);
        annonceView.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                AnnonceView annonce = new AnnonceView(user, annonceEntity);
                annonce.pack();
                annonce.setAlwaysOnTop(true);
                annonce.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                //
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                //
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                //
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                //
            }
        });
    }

    public void addLogo(String path) {
        /**
         * AJout + redimensionnement de l'image
         */
        logo = new ImageIcon(path); // load the image to a imageIcon
        Image image = logo.getImage(); // transform it
        Image newimg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH); // scale it the smooth way
        logo = new ImageIcon(newimg);  // transform it back

        JLabel labelImg = new JLabel(logo);

        imagePanel.add(labelImg);
        imagePanel.updateUI();
    }
}
