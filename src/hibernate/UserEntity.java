package hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "iduser", nullable = false)
    private int idUser;

    @Column(name="nom", nullable = false)
    private String nom;

    @Column(name="prenom", nullable = false)
    private String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        Pattern p = Pattern.compile(".*@.*\\..+");
        Matcher m = p.matcher(mail);
        if(m.matches()) {
            this.mail = mail;
        }
    }

    @Column(name="mail")
    private String mail;

    public int getIduser() {
        return idUser;
    }

    public void setIduser(int idUser) {
        this.idUser = idUser;
    }

}
