package view;

import DAO.AnnonceDAO;
import DAO.CommentDAO;
import DAO.ImageDAO;
import DAO.UserDAO;
import hibernate.AnnonceEntity;
import hibernate.CommentEntity;
import hibernate.UserEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AnnonceView extends JFrame{
    private JLabel lb_prix;
    private JLabel lb_commentaire;
    private JLabel lb_titre;
    private JPanel jp_image;
    private JButton acheterButton;
    public JPanel jp_annonce;
    private JList list_comment;
    private JTextArea ta_comment;
    private JButton envoyerUnCommentaireButton;
    private JTextArea ta_description;
    private JLabel sold;
    private java.util.List<CommentItem> tabComment = new ArrayList<>();

    public AnnonceView(){
        add(jp_annonce);
        setTitle("Le détail d'une annonce");
        setPreferredSize(new Dimension(1500, 1000));

        AnnonceDAO annonceDAO = new AnnonceDAO();
        UserDAO userDAO = new UserDAO();
        ImageDAO imageDAO = new ImageDAO();
        AnnonceEntity annonceEntity = annonceDAO.fetch(1);
        CommentDAO commentDAO = new CommentDAO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String today = LocalDate.now().format(dateTimeFormatter);
        java.util.List<CommentEntity> comment = commentDAO.fetchAll();
        for (CommentEntity coEntity : comment) {
            UserEntity userEntity = userDAO.fetch(coEntity.getIdUser());
            CommentItem newComment = new CommentItem(coEntity.getComment(), userEntity.getNom(), today);
            tabComment.add(newComment);
        }
        list_comment.setListData(tabComment.toArray());
        String path = imageDAO.fetchbyAnnonceId(annonceEntity.getIdAnnonce()).getPath();
        File selectedFile = new File(path);
        try {
            BufferedImage myPicture = ImageIO.read(selectedFile);
            ImageIcon pic = new ImageIcon(myPicture);
            Image image = pic.getImage();
            Image newimg = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            pic = new ImageIcon(newimg);
            JLabel jLabImg = new JLabel(pic);
            jp_image.checkImage(newimg, null);
            jp_image.add(jLabImg);
            jp_image.updateUI();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        lb_titre.setText(annonceEntity.getTitle());
        lb_prix.setText(Float.toString(annonceEntity.getPrice()));
        ta_description.setText(annonceEntity.getDescription());
        if (annonceEntity.isSold()) {
            sold.setText("Vendu");
            acheterButton.setEnabled(false);
            lb_prix.setText("Objet vendu");
        } else {
            sold.setText("A vendre");
        }

        acheterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int dialogResult = JOptionPane.showConfirmDialog(jp_annonce, "Confirmer l'achat de  " + annonceEntity.getTitle() + " ?");
                if (dialogResult == JOptionPane.OK_OPTION) {
                    annonceEntity.setSold(true);
                    sold.setText("Vendu");
                    lb_prix.setText("Objet vendu");
                    acheterButton.setEnabled(false);
                    annonceDAO.update(annonceEntity);
                } else {
                    return;
                }
            }
        });

        envoyerUnCommentaireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserEntity user = userDAO.fetch(annonceEntity.getIdUser());
                CommentItem currentComment = new CommentItem(ta_comment.getText(), user.getNom(), today);
                CommentEntity comment = new CommentEntity(annonceEntity.getIdAnnonce(), user.getIdUser(), LocalDate.now(), ta_comment.getText());
                commentDAO.create(comment);
                tabComment.add(currentComment);
                list_comment.setListData(tabComment.toArray());
                ta_comment.setText("");

            }
        });
    }
}
