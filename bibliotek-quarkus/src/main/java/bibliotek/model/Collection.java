package bibliotek.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import bibliotek.view.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="collection")
public class Collection {

	@Id
	@UuidGenerator
	@JsonView(Views.Common.class)
	private String id;
	@Column(length = 50,nullable = false)
	@JsonView(Views.Common.class)
	private String nom;
	@OneToMany(mappedBy="collection")
	@JsonView(Views.CollectionWithLivre.class)
	private List<Livre> livres= new ArrayList();

	public Collection(String id, String nom, List<Livre> livres) {
		this.id = id;
		this.nom = nom;
		this.livres = livres;
	}

	public Collection() {
	}

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

	public List<Livre> getLivres() {
		return livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	@Override
	public String toString() {
		return "Collection [id=" + id + ", nom=" + nom + "]";
	}

}
