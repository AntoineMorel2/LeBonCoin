package hibernate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "annonce")
public class AnnonceEntity {

    @Id
    @Column(name="idannonce", nullable = false)
    private int idAnnonce;
    @Column(name="iduser", nullable = false)
    private int idUser;
    @Column(name="idcategory", nullable = false)
    private int idCategory;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "sold")
    private boolean sold;
    @Column(name = "datecreation", nullable = false)
    private LocalDate dateCreation;
    @Column(name = "price")
    private float price;

    public AnnonceEntity() {
    }

    public AnnonceEntity(int idUser, int idCategory, String title, String description, boolean sold, LocalDate dateCreation, float price) {
        this.idUser = idUser;
        this.idCategory = idCategory;
        this.title = title;
        this.description = description;
        this.sold = sold;
        this.dateCreation = dateCreation;
        this.price = price;
    }

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

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
