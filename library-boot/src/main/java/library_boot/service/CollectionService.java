package library_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library_boot.dao.IDAOCollection;
import library_boot.model.Collection;

@Service
public class CollectionService {

	
	@Autowired
	IDAOCollection daoCollection;

	public Collection getById(Integer id) throws RuntimeException
	{
		if(id==null) 
		{
			throw new RuntimeException("L'id d'une collection ne peut pas etre null");	
		}
		Optional <Collection> opt = daoCollection.findById(id);
		if(opt.isEmpty()) {return null;}
		else {return opt.get();}
	}

	public List<Collection> getAll()
	{
		return daoCollection.findAll();
	}
	
	public Collection create(Collection collection) 
	{
		return daoCollection.save(collection);
	}

	public Collection update(Collection collection) 
	{
		return daoCollection.save(collection);
	}

	public void deleteById(Integer id) 
	{
		daoCollection.deleteById(id);
	}

	public void delete(Collection collection)
	{
		daoCollection.delete(collection);
	}
	
}

