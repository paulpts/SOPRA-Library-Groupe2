package library_boot.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import library_boot.view.Views;


@Entity
@Table(name="livre")
public class Livre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	protected Integer id;

	@Column(length = 50,nullable = false)
	@JsonView(Views.Common.class)
	protected String titre;
	
	@Column(length = 300,nullable = false)
	@JsonView(Views.Common.class)
	protected String resumer;
	
	@Column(nullable = false)
	@JsonView(Views.Common.class)
	protected int annee;
	
	@ManyToOne
	@JoinColumn(name="auteur",nullable = false)
	@JsonView(Views.Livre.class)
	protected Auteur auteur;
	
	@ManyToOne
	@JoinColumn(name="editeur",nullable = false)
	@JsonView(Views.Livre.class)
	protected Editeur editeur;
	
	@ManyToOne
	@JoinColumn(name="collection",nullable = false)
	@JsonView(Views.Livre.class)
	protected Collection collection;
	
	@ManyToOne
	@JoinColumn(name="genre",nullable = false)
	@JsonView(Views.Livre.class)
	protected Genre genre;
	
	
	public Livre() {}
	
	
	
	public Livre(String titre, String resumer, int annee, Auteur auteur, Editeur editeur, Collection collection,
			Genre genre) {
		this.titre = titre;
		this.resumer = resumer;
		this.annee = annee;
		this.auteur = auteur;
		this.editeur = editeur;
		this.collection = collection;
		this.genre = genre;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getResumer() {
		return resumer;
	}

	public void setResumer(String resumer) {
		this.resumer = resumer;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", resumer=" + resumer + ", annee=" + annee + ", collection="
				+ collection + "]";
	}
	
	
	
	
	

	
	
	

}
