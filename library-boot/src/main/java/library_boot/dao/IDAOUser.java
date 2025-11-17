package library_boot.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import library_boot.model.User;

public interface IDAOUser extends JpaRepository<User,Integer> {
	
	public Optional<User> findByLogin(String login);

}
