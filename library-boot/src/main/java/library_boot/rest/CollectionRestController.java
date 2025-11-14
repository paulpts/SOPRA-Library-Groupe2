package library_boot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library_boot.model.Collection;
import library_boot.service.CollectionService;

@RestController
@RequestMapping("/api/collection")
public class CollectionRestController {

	
	@Autowired
	CollectionService collectionSrv;


	@GetMapping
	public List<Collection> allCollections()
	{
		return collectionSrv.getAll();
	}

	@GetMapping("/{id}")
	public Collection ficheCollection(@PathVariable Integer id, Collection matiere) {
		return collectionSrv.getById(id);
	}


	@PostMapping
	public Collection ajoutCollection(@RequestBody Collection matiere)
	{
		return collectionSrv.create(matiere);
	}


	@PutMapping("/{id}")
	public Collection modifierCollection(@PathVariable Integer id,@RequestBody Collection collection)
	{
		collection.setId(id);
		return (Collection) collectionSrv.update(collection);
	}


	@DeleteMapping("/{id}")
	public void supprimerCollection(@PathVariable Integer id) {
		collectionSrv.deleteById(id);
	}

}

