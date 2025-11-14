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

import library_boot.model.Auteur;
import library_boot.service.AuteurService;

@RestController
@RequestMapping("/api/auteur")
public class AuteurRestController {
	
	@Autowired
	private AuteurService autservice;
	
	@GetMapping
	public List<Auteur> allAuteurs()
	{
		return autservice.getAll();
	}

	@GetMapping("/{id}")
	public Auteur ficheAuteur(@PathVariable Integer id)
	{
		return autservice.getById(id);
	}
	
	@PostMapping
	public Auteur createAuteur(@RequestBody Auteur auteur)
	{
		return autservice.create(auteur);
	}
	
	@PutMapping("/{id}")
	public Auteur modifierAuteur(@PathVariable Integer id,@RequestBody Auteur auteur)
	{
		auteur.setId(id);
		return autservice.update(auteur);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAuteur(@PathVariable Integer id)
	{
		autservice.deleteById(id);
	}
	

}
