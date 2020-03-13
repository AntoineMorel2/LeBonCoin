package hibernate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "annonce")
public class AnnonceEntity {

    @Id
    @Column(name="idAnnonce", nullable = false)
    private int idAnnonce;
    @Column(name="iduser", nullable = false)
    private int idUser;
    @Column(name="idCategory", nullable = false)
    private int idCategory;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="sold")
    private boolean sold;
    @Column(name="datecreation", nullable = false)
    private Timestamp dateCreation;
    @Column(name="price")
    private float price;

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
