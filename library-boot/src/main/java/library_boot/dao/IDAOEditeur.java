package library_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import library_boot.model.Editeur;

public interface IDAOEditeur extends JpaRepository<Editeur, Integer> {

	
}
