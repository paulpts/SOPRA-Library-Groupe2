package library_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import library_boot.model.Genre;

public interface IDAOGenre extends JpaRepository<Genre,Integer> {

}
