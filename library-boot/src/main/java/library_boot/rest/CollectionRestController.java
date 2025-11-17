package library_boot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import library_boot.model.Collection;
import library_boot.service.CollectionService;
import library_boot.view.Views;

@RestController
@RequestMapping("/api/collection")
@CrossOrigin(origins = "http://localhost:4200")
public class CollectionRestController {

	
	@Autowired
	CollectionService collectionSrv;

	 @JsonView(Views.Collection.class)
	@GetMapping
	public List<Collection> allCollections()
	{
		return collectionSrv.getAll();
	}

	 @JsonView(Views.Collection.class)
	@GetMapping("/{id}")
	public Collection ficheCollection(@PathVariable Integer id, Collection matiere) {
		return collectionSrv.getById(id);
	}

	 @JsonView(Views.Collection.class)
	@PostMapping
	public Collection ajoutCollection(@RequestBody Collection matiere)
	{
		return collectionSrv.create(matiere);
	}

	 @JsonView(Views.Collection.class)
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

