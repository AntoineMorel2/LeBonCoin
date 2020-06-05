package DAO;

import hibernate.AnnonceEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.List;

/**
 * @author AMorel / VPingrenon
 * Cette DAO est utilisée pour toutes les requêtes en rapport avec les Annonces
 */
public class AnnonceDAO extends DAO<AnnonceEntity>{

    /**
     * Créé un nouvel objet Annonce dans la base de donnée
     * @param obj l'objet a créer dans la base de donnée
     * @return un booléen si la création a fonctionné
     */
    @Override
    public boolean create(AnnonceEntity obj) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch(RollbackException r){
            if(entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } catch(PersistenceException e){
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * Cette méthode permet de récupèrer toutes les annonces créée par un utilisateur .
     * @param idUser l'id de l'utiliseur dont nous voulons les annonces
     * @return une liste d'AnnonceEntité
     */
    @SuppressWarnings("unchecked")
    public List<AnnonceEntity> fetchFromUser(int idUser){
        EntityManager entityManager = null;
        try{
            entityManager = getEntityManager();
            final Query query;
            query = entityManager.createQuery("SELECT a FROM AnnonceEntity a WHERE :user  LIKE :idUser",AnnonceEntity.class);
            query.setParameter("user","iduser");
            query.setParameter("idUser",idUser);
            return (List<AnnonceEntity>) query.getResultList();
        } catch(PersistenceException e){
            System.out.println("Unable to fetch the AnnonceEntity from the user with id " + idUser);
            return null;
        }
        finally{
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Recupère une AnnonceEntité liée a l'id fournie
     * @param id l'id dont nous voulons l'AnnonceEntité
     * @return Une AnnonceEntité ou null.
     */
    @Override
    public AnnonceEntity fetch(int id) {
        EntityManager entityManager = null;
        try{
            entityManager = getEntityManager();
            return entityManager.find(AnnonceEntity.class,id);
        } catch(PersistenceException e){
            System.out.println("Unable to fetch the AnnonceEntity with the following id " + id);
            return null;
        }
        finally{
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Recupère une Liste de toutes les AnnonceEntité dans la base de donnée
     * @return Une liste d'AnnonceEntité ou null
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AnnonceEntity> fetchAll() {
        EntityManager entityManager = null;
        try{
            entityManager = getEntityManager();
            final Query query;
            query = entityManager.createQuery("SELECT a FROM AnnonceEntity a",AnnonceEntity.class);
            return (List<AnnonceEntity>) query.getResultList();
        } catch(PersistenceException e){
            System.out.println("Unable to fetch all the AnnonceEntity");
            return null;
        }
        finally{
            if(entityManager != null){
            entityManager.close();
            }
        }
    }

    /**
     * Récupère toutes les AnnonceEntité qui contiennent une certaines suite de mot dans leurs titre
     * @param name La suite de mot qui doit être contenu dans le titre
     * @return Une liste d'AnnonceEntité
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AnnonceEntity> fetchByName(String name) {
        EntityManager entityManager = null;
        try{
            entityManager = getEntityManager();
            final Query query;
            query = entityManager.createQuery("SELECT a FROM AnnonceEntity a WHERE :nom LIKE :name", AnnonceEntity.class);
            query.setParameter("nom", "name");
            query.setParameter("name","%"+name+"%");
            return (List<AnnonceEntity>) query.getResultList();
        } catch(PersistenceException e){
            System.out.println("Unable to fetch the AnnonceEntity with the following name " + name);
            return null;
        }
        finally{
            if(entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Met a jour l'objet passé en paramètre dans la base de donnée
     * @param obj l'objet a mettre a jour
     * @return True si la mise à jour s'est bien passée, false sinon
     */
    @Override
    public boolean update(AnnonceEntity obj) {
        EntityManager entityManager = null;
        try {
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch(RollbackException r){
            if(entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } catch(PersistenceException e){
            System.out.println("Problem occurred while creating the object in database");
            return false;
        } finally {
            if(entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * Supprime l'objet donné en paramètre dans la base de donnée
     * @param obj L'objet a supprimé dans la base de donnée
     * @return True si la suppression a été reussie, false sinon
     */
    @Override
    public boolean delete(AnnonceEntity obj) {
        EntityManager entityManager = null;
        try{
            entityManager = getEntityManager();
            entityManager.getTransaction().begin();
            final Query query;
            query = entityManager.createQuery("DELETE FROM AnnonceEntity WHERE :annonce LIKE :idToDelete ",AnnonceEntity.class);
            query.setParameter("annonce","idannonce");
            query.setParameter("idToDelete", obj.getIdAnnonce());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch(RollbackException r){
            if(entityManager !=null) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Could not delete the Annonce with title " + obj.getTitle() );
            return false;
        } catch(PersistenceException e){
            System.out.println("Could not delete the Annonce with title " + obj.getTitle() );
            return false;
    }
        finally{
            if(entityManager != null) {
                entityManager.close();
            }
        }

    }
}
