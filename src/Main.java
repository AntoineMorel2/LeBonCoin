import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.CreateAnnonce;
import view.SignInView;
import view.SignUpView;

import javax.persistence.metamodel.EntityType;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static SignInView signInView;
    private static SignUpView annonceView;

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
           /* System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }*/
            //JFrame frame = new JFrame("AccueilView");
            /*signInView.setContentPane(signInView.accueil);
            signInView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            signInView.pack();
            signInView.setVisible(true);*/
            annonceView = new SignUpView();
            annonceView.setContentPane(annonceView.signUpPanel);
            annonceView.pack();
            annonceView.setVisible(true);
        } finally {
            session.close();
        }
    }
}