package view;

import DAO.AnnonceDAO;
import DAO.CategoryDAO;
import DAO.ImageDAO;
import hibernate.AnnonceEntity;
import hibernate.CategoryEntity;
import hibernate.ImageEntity;
import hibernate.UserEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.util.List;
import java.util.UUID;

public class CreateAnnonce extends JFrame {

    public JPanel createannonce;
    private JTextField tf_titre;
    private JTextField tf_prix;
    private JTextArea ta_desciption;
    private JButton ajouterLAnnonceButton;
    private JButton ajouterUneImageButton;
    private JPanel jp_image;
    private JComboBox comboBoxCategories;
    private JLabel categorie;

    public CreateAnnonce(UserEntity userConnected) {
        add(createannonce);

        setTitle("Création d'une annonce");

        ta_desciption.setLineWrap(true);
        ta_desciption.setWrapStyleWord(true);

        AnnonceDAO annonceDAO = new AnnonceDAO();
        ImageDAO imageDAO = new ImageDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        AnnonceEntity annonceEntity = new AnnonceEntity();

        // Init image par défaut
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setIdAnnonce(annonceEntity.getIdAnnonce());
        imageEntity.setPath("");
        imageEntity.setName("");

        setPreferredSize(new Dimension(500, 500));

        // Remplissage JComboBox
        List<CategoryEntity> categoryEntityList = categoryDAO.fetchAll();
        for(CategoryEntity categoryEntity : categoryEntityList) {
            comboBoxCategories.addItem(categoryEntity.getName());
        }

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
                        Path first = Path.of(selectedFile.toURI());
                        Path second = Path.of(f.toURI());
                        Files.copy(first, second);

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
                if(titre.isEmpty() || prix.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(createannonce, "Veuillez renseigner tous les champs");
                    return;
                }
                if(!estUnEntier(prix)) {
                    JOptionPane.showMessageDialog(createannonce, "Veuillez renseigner un prix valide");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(createannonce, "Confirmer l'ajout de l'annonce " + titre + " ?");
                if (confirm == JOptionPane.OK_OPTION) {
                    // init annonceEntity pour création
                    annonceEntity.setIdUser(userConnected.getIdUser());
                    // On n'a rien vu
                    LocalDate now = LocalDate.now().plusDays(1);
                    annonceEntity.setDateCreation(now);
                    annonceEntity.setTitle(tf_titre.getText());
                    annonceEntity.setDescription(ta_desciption.getText());
                    annonceEntity.setSold(false);
                    annonceEntity.setPrice(Float.parseFloat(tf_prix.getText()));
                    annonceEntity.setIdCategory(categoryDAO.fetchByName((String) comboBoxCategories.getSelectedItem()).get(0).getIdCategory());

                    if (!annonceDAO.create(annonceEntity)) {
                        JOptionPane.showMessageDialog(createannonce, "Une erreur s'est produite pendant la création de l'annonce.");
                        return;
                    }
                    imageEntity.setIdAnnonce(annonceDAO.fetchLast());
                    if (!imageDAO.create(imageEntity)) {
                        JOptionPane.showMessageDialog(createannonce, "Une erreur s'est produite pendant l'export de l'image.");
                        return;
                    }
                    dispose();
                }
            }
        });
    }

    public boolean estUnEntier(String chaine) {
        try {
            Float.parseFloat(chaine);
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }
}
