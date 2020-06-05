import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.CreateAnnonce;
import view.SignInView;

import javax.swing.*;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static SignInView signInView;
    private static CreateAnnonce createannonce;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            signInView = new SignInView();
            System.out.println("querying all the managed entities...");
           //accueilView = new AccueilView();
            //createannonce = new CreateAnnonce();
            //JFrame frame = new JFrame("AccueilView");
            signInView.setContentPane(signInView.accueil);
            signInView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signInView.pack();
            signInView.setVisible(true);
//            accueilView.setContentPane(accueilView.accueil);
//            accueilView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            accueilView.pack();
//            accueilView.setVisible(true);

           /* createannonce.setContentPane(createannonce.createannonce);
            createannonce.pack();
            createannonce.setVisible(true);*/
        } finally {
            session.close();
        }
    }
}