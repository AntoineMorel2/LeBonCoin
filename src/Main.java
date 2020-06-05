import DAO.*;

public class Main {

    public static void main(final String[] args) {

        /*
        UserEntity user1 = new UserEntity("Vert chat voeux", "Valentin", "valentin.jmenbalek@gmail.com", "123");
        UserEntity user2 = new UserEntity("Mot Relle", "Sur?", "antoinemorel78@gmail.com", "456");
        UserEntity user3 = new UserEntity("Roux selle", "Pranko", "jaiPeurDesFantomes@gmail.com", "789");
        UserEntity user4 = new UserEntity("Pain Grr euh non", "Vingt centimes", "onMangeQuand@gmail.com", "password");
        UserDAO uDAO = new UserDAO();
        uDAO.create(user1);
        uDAO.create(user2);
        uDAO.create(user3);
        uDAO.create(user4);

         */

        /*
        CategoryEntity category1 = new CategoryEntity("BBC");
        CategoryEntity category2 = new CategoryEntity("TF1");
        CategoryEntity category3 = new CategoryEntity("CNN");
        CategoryEntity category4 = new CategoryEntity("AL Jazeera");
        CategoryEntity category5 = new CategoryEntity("BFMTV");
        CategoryDAO categoryDAO= new CategoryDAO();
        categoryDAO.create(category1);
        categoryDAO.create(category2);
        categoryDAO.create(category3);
        categoryDAO.create(category4);
        categoryDAO.create(category5);

         */
        /*
        AnnonceEntity a1 = new AnnonceEntity(1, 1, "Raoult cherche la merde","Le docteur Raoult cherche quelque chose qu'il peut enfin trouver correctement: la merded", false, LocalDate.now(), 12);
        AnnonceDAO aDAO = new AnnonceDAO();
        aDAO.create(a1);

         */

        /*
        CommentEntity comment1 = new CommentEntity(1, 2, LocalDate.now(), "Haha, bien fait pour sa gueule, il le mérite ce chercheur de pacotille.");
        CommentEntity comment2 = new CommentEntity(1, 3, LocalDate.now(), "Ouais, j'avoue, il a abusé le mec!");
        CommentDAO cDAO = new CommentDAO();
        cDAO.create(comment1);
        cDAO.create(comment2);

         */

        /*
        ImageEntity img1 = new ImageEntity(1, "../../pictures/Raoult.jpg", "Raoult");
        ImageDAO imgDAO = new ImageDAO();
        imgDAO.create(img1);

         */

        UserDAO uDAO = new UserDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        AnnonceDAO aDAO = new AnnonceDAO();
        CommentDAO cDAO = new CommentDAO();
        ImageDAO imgDAO = new ImageDAO();


    }
}