package library_boot.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonView(Views.Common.class)
	private Integer id;
	
	@Column(length = 50,nullable = false)
	//@JsonView(Views.Common.class)
	private String libelle;
	
	
	@OneToMany(mappedBy="genre")
	private List<Livre> livres= new ArrayList();

	
	
	public Genre() {}



	public Genre(Integer id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public List<Livre> getLivres() {
		return livres;
	}



	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}



	@Override
	public String toString() {
		return "Genre [id=" + id + ", libelle=" + libelle + "]";
	}
		
	
	
	
	
}
