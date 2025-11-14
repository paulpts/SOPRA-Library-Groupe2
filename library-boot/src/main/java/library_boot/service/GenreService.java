package library_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library_boot.dao.IDAOGenre;
import library_boot.model.Genre;


@Service
public class GenreService {

	@Autowired
	IDAOGenre daoGenre;

	public Genre getById(Integer id)
	{
		Optional <Genre> opt = daoGenre.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Genre> getAll()
	{
		return daoGenre.findAll();
	}

	public Genre create(Genre Genre) 
	{
		return daoGenre.save(Genre);
	}

	public Genre update(Genre Genre) 
	{
		return daoGenre.save(Genre);
	}

	public void deleteById(Integer id) 
	{
		daoGenre.deleteById(id);
	}

	public void delete(Genre Genre)
	{
		daoGenre.delete(Genre);
	}
	
}
