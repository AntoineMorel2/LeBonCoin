package view;

import DAO.*;
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

public class AnnonceView extends JFrame{
    private JLabel lb_prix;
    private JLabel lb_commentaire;
    private JLabel lb_titre;
    private JPanel jp_image;
    private JButton acheterButton;
    public JPanel jp_annonce;
    private JTextArea ta_comment;
    private JButton envoyerUnCommentaireButton;
    private JTextArea ta_description;
    private JLabel sold;
    private JScrollPane scrollCommentaire;
    private JPanel commentaires;
    private JLabel lb_categorie;
    private JLabel categorie;
    private static int index = 0;

    public AnnonceView(UserEntity user, AnnonceEntity annonceEntity) {
        add(jp_annonce);
        ta_comment.setLineWrap(true);
        setTitle("Le détail d'une annonce");
        setPreferredSize(new Dimension(1500, 600));
        // redefinir le layout permet de placer les éléments soient les uns en dessous des autres
        commentaires.setPreferredSize(new Dimension(900, 800));
        commentaires.setLayout(new BoxLayout(commentaires, BoxLayout.Y_AXIS));

        UserDAO userDAO = new UserDAO();
        ImageDAO imageDAO = new ImageDAO();
        CommentDAO commentDAO = new CommentDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String today = LocalDate.now().format(dateTimeFormatter);
        java.util.List<CommentEntity> comment = commentDAO.fetchAllById(annonceEntity.getIdAnnonce());
        for (CommentEntity coEntity : comment) {
            UserEntity userEntity = userDAO.fetch(coEntity.getIdUser());
            CommentItem newComment = new CommentItem(coEntity.getComment(), userEntity.getNom(), today);
            newComment.setForeground(Color.white);
            commentaires.add(newComment);
        }
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
        categorie.setText(categoryDAO.fetch(annonceEntity.getIdCategory()).getName());
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
                    AnnonceDAO annonceDAO = new AnnonceDAO();
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
                currentComment.setForeground(Color.white);
                commentaires.add(currentComment);
                commentaires.updateUI();
                ta_comment.setText("");

            }
        });
    }
}
