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

import com.fasterxml.jackson.annotation.JsonView;

import library_boot.model.Livre;
import library_boot.service.LivreService;
import library_boot.view.Views;

@RestController
@RequestMapping("/api/livre")
public class LivreRestController {
	
	@Autowired
	LivreService livreService;
	
	@JsonView(Views.Livre.class)
	@GetMapping
	public List<Livre> allLivres()
	{
		return livreService.getAll();
	}

	@JsonView(Views.Livre.class)
	@GetMapping("/{id}")
	public Livre ficheLivre(@PathVariable Integer id) {
		return livreService.getById(id);
	}


	@JsonView(Views.Livre.class)
	@PostMapping
	public Livre ajoutLivre(@RequestBody Livre livre)
	{
		return livreService.create(livre);
	}


	@JsonView(Views.Livre.class)
	@PutMapping("/{id}")
	public Livre modifierLivre(@PathVariable Integer id,@RequestBody Livre livre)
	{
		livre.setId(id);
		return (Livre) livreService.update(livre);
	}


	@DeleteMapping("/{id}")
	public void supprimerLivre(@PathVariable Integer id) {
		livreService.deleteById(id);
	}


}
