package library_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import library_boot.model.Auteur;

public interface IDAOAuteur extends JpaRepository<Auteur,Integer> {

}
