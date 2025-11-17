package library_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import library_boot.model.Collection;

public interface IDAOCollection extends JpaRepository<Collection, Integer>{

	
}
