package view;

import DAO.AnnonceDAO;
import DAO.ImageDAO;
import hibernate.AnnonceEntity;
import hibernate.ImageEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class CreateAnnonce extends JFrame {

    public JPanel createannonce;
    private JTextField tf_titre;
    private JTextField tf_prix;
    private JTextArea ta_desciption;
    private JButton ajouterLAnnonceButton;
    private JButton ajouterUneImageButton;
    private JPanel jp_image;

    public CreateAnnonce() {
        add(createannonce);

        setTitle("Création d'une annonce");

        AnnonceDAO annonceDAO = new AnnonceDAO();
        ImageDAO imageDAO = new ImageDAO();

        AnnonceEntity annonceEntity = new AnnonceEntity();

        // Init image par défaut
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setIdAnnonce(annonceEntity.getIdAnnonce());
        imageEntity.setPath("../ressources/logo.png");
        imageEntity.setName("logo.png");

        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JScrollPane scrollPane = new JScrollPane(ta_desciption);
//        add(scrollPane);

        ajouterUneImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnvalue = fc.showOpenDialog(createannonce);
                if(returnvalue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fc.getSelectedFile();
                    JOptionPane.showMessageDialog(createannonce, "chemin du fichier :" + selectedFile);

                    try {
                        BufferedImage myPicture = ImageIO.read(selectedFile);
                        String filename = UUID.randomUUID().toString();
                        File f = new File("src/ressources/", filename + ".jpg");
                        System.out.println(f.getPath());

                        f.createNewFile();

                        ImageIO.write(myPicture, "jpg", f);

//                      new ImageEntity(f.getPath(), selectedFile.getName()));
                        imageEntity.setName(filename);
                        imageEntity.setPath(f.getPath());

                        ImageIcon pic = new ImageIcon(myPicture);
                        Image image = pic.getImage();
                        Image newimg = image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
                        pic = new ImageIcon(newimg);
                        JLabel jLabImg = new JLabel(pic);
                        jp_image.checkImage(newimg, null);
                        jp_image.add(jLabImg);
                        jp_image.updateUI();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                }

            }
        });
        ajouterLAnnonceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titre = tf_titre.getText();
                String prix = tf_prix.getText();
                String description = ta_desciption.getText();
                JOptionPane.showMessageDialog(createannonce, "Données reçus : titre = " + titre + " prix = " + prix + " description = " + description);
                JOptionPane.showConfirmDialog(createannonce, "Confirmer l'ajout de l'annonce " + titre + " ?");

                // init annonceEntity pour création
                annonceEntity.setIdCategory(1);
                annonceEntity.setIdUser(1);
                annonceEntity.setDateCreation(LocalDate.now());
                annonceEntity.setTitle(tf_titre.getText());
                annonceEntity.setDescription(ta_desciption.getText());
                annonceEntity.setSold(false);
                annonceEntity.setPrice(Float.parseFloat(tf_prix.getText()));

                System.out.println(imageEntity);
                System.out.println(imageEntity.getIdImage() + " " + imageEntity.getIdAnnonce() + " " + imageEntity.getName() + " " + imageEntity.getPath());


                if (!annonceDAO.create(annonceEntity)) {
                    JOptionPane.showMessageDialog(createannonce, "Une erreur s'est produite pendant la création de l'annonce.");
                }
                imageEntity.setIdAnnonce(annonceDAO.fetchLast());
                if (!imageDAO.create(imageEntity)) {
                    JOptionPane.showMessageDialog(createannonce, "Une erreur s'est produite pendant l'export de l'image.");
                }
            }
        });
    }
}
