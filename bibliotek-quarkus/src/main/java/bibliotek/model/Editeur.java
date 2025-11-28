package bibliotek.model;

import org.hibernate.annotations.UuidGenerator;

import bibliotek.enumerator.NationaliteEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Table(name = "editeur")
public class Editeur {
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private NationaliteEnum pays;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NationaliteEnum getPays() {
        return pays;
    }

    public void setPays(NationaliteEnum pays) {
        this.pays = pays;
    }
}
