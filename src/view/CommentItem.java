package view;

import javax.swing.*;
import java.awt.*;

public class CommentItem extends JPanel{
    private JPanel jp_comment;
    private JLabel lb_nom;
    private JLabel lb_date;
    private JLabel lb_comment;


    public CommentItem(){
        add(jp_comment);
        setPreferredSize(new Dimension(100, 50));
    }


    public void setLb_nom(String lb_nom) {
        this.lb_nom.setText(lb_nom);
    }

    public void setLb_date(String lb_date) {
        this.lb_date.setText(lb_date);
    }

    public void setLb_comment(String lb_comment) {
        this.lb_comment.setText(lb_comment);
    }
}
