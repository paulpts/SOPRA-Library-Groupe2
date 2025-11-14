package library_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import library_boot.model.Livre;

public interface IDAOLivre  extends JpaRepository<Livre, Integer>{
	
	

}
