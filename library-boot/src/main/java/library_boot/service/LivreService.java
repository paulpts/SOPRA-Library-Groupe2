package library_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library_boot.dao.IDAOLivre;
import library_boot.model.Livre;


@Service
public class LivreService {
	
	@Autowired
	IDAOLivre daoLivre;


	public Livre getById(Integer id)
	{
		Optional<Livre> opt = daoLivre.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}


	public List<Livre> getAll()
	{
		return daoLivre.findAll();
	}

	public Livre create(Livre livre)
	{
		if(livre.getId()!=null) 
		{
			throw new RuntimeException("On ne peut pas ajouter un livre qui a déjà un id ?!");
		}
		return daoLivre.save(livre);
	}

	public Livre update(Livre livre)
	{
		if(livre.getId()==null) 
		{
			throw new RuntimeException("On ne peut pas modifier un livre sans id !");
		}
		return daoLivre.save(livre);
	}

	
	public void deleteById(Integer id) 
	{
		daoLivre.deleteById(id);
	}

	public void delete(Livre livre)
	{
		daoLivre.delete(livre);
	}
	
	

}
