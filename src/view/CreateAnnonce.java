package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateAnnonce extends JFrame {

    public JPanel createannonce;
    private JTextField tf_titre;
    private JTextField tf_prix;
    private JTextArea ta_desciption;
    private JButton ajouterLAnnonceButton;
    private JButton ajouterUneImageButton;
    private JPanel jp_image;

    public CreateAnnonce(){
        add(createannonce);

        setTitle("Création d'une annonce");

        setPreferredSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JScrollPane scrollPane = new JScrollPane(ta_desciption);
//        add(scrollPane);

        ajouterUneImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnvalue = fc.showOpenDialog(createannonce);
                if(returnvalue == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fc.getSelectedFile();
                    try {
                        BufferedImage myPicture = ImageIO.read(selectedFile);
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
                JOptionPane.showMessageDialog(createannonce, "Données reçus : titre = "+titre+" prix = "+prix+" description = "+description);
                JOptionPane.showConfirmDialog(createannonce, "Confirmer l'ajout de l'annonce "+titre+" ?");
            }
        });
    }
}
