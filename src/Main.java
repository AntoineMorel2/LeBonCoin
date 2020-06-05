import view.SignInView;

import javax.swing.*;

public class Main {
    private static SignInView signInView;

    public static void main(final String[] args) {
            signInView = new SignInView();
            signInView.setContentPane(signInView.accueil);
            signInView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signInView.pack();
            signInView.setVisible(true);
    }
}