package library_boot.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="livre")
public class Livre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(length = 50,nullable = false)
	protected String titre;
	
	@Column(length = 300,nullable = false)
	protected String resumer;
	
	@Column(nullable = false)
	protected LocalDate annee;
	
	@ManyToOne
	@JoinColumn(name="auteur",nullable = false)
	protected Auteur auteur;
	
	@ManyToOne
	@JoinColumn(name="editeur",nullable = false)
	protected Editeur editeur;
	
	@ManyToOne
	@JoinColumn(name="collection",nullable = false)
	protected Collection collection;
	
	@ManyToOne
	@JoinColumn(name="genre",nullable = false)
	protected Genre genre;
	
	
	public Livre() {};
	
	
	
	public Livre(String titre, String resumer, LocalDate annee, Auteur auteur, Editeur editeur, Collection collection,
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

	public LocalDate getAnnee() {
		return annee;
	}

	public void setAnnee(LocalDate annee) {
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
