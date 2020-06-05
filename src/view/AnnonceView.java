package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class AnnonceView extends JFrame{
    private JLabel lb_prix;
    private JLabel lb_commentaire;
    private JLabel lb_titre;
    private JPanel jp_image;
    private JButton acheterButton;
    private JPanel jp_annonce;
    private JList list_comment;
    private JTextArea ta_comment;
    private JButton envoyerUnCommentaireButton;
    private JTextArea ta_description;
    private ArrayList<CommentItem> tabComment;

    public AnnonceView(){
        add(jp_annonce);

        setTitle("Le détail d'une annonce");
        setPreferredSize(new Dimension(500, 500));
        envoyerUnCommentaireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommentItem currentComment = new CommentItem();
                currentComment.setLb_comment(ta_comment.getText());
                Date date = new Date();
                currentComment.setLb_date(date.toString());
                currentComment.setLb_nom("Un nom");
                tabComment.add(currentComment);
                tabComment.forEach(commentItem -> list_comment.add(commentItem));

            }
        });
        acheterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
