package library_boot.model;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import library_boot.view.Views;


@Entity
@Table(name = "editeur")
public class Editeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.Common.class)
    private Integer id;

    @Column(name = "nom", length = 100, nullable = false)
    @JsonView(Views.Common.class)
    private String nom;

    @Column(name = "pays", length = 100, nullable = false)
    @JsonView(Views.Common.class)
    private String pays;

    @OneToMany(mappedBy = "editeur")
    @JsonView(Views.EditeurWithLivre.class)
    private List<Livre> livres= new ArrayList();

    public Editeur() {
    }

    public Editeur(Integer id, String nom, String pays) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
    }

    public Editeur(String nom, String pays) {
        this.nom = nom;
        this.pays = pays;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

   

    public String getInfos() {
        return this.id + " - " + this.nom + " (" + this.pays + ")";
    }

    @Override
    public String toString() {
        return "Editeur [id=" + id + ", nom=" + nom + ", pays=" + pays + "]";
    }
}
	

