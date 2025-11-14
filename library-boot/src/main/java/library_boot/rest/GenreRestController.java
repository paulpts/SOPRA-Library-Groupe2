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


import library_boot.model.Genre;
import library_boot.service.GenreService;

@RestController
@RequestMapping("/api/Genre")
public class GenreRestController {
	@Autowired
	GenreService genreSrv;

	@GetMapping
	public List<Genre> allGenres()
	{
		return genreSrv.getAll();
	}

	@GetMapping("/{id}")
	public Genre ficheGenre(@PathVariable Integer id)
	{
		return genreSrv.getById(id);
	}

	@PostMapping
	public Genre ajouterGenre(@RequestBody Genre Genre)
	{
		return (Genre) genreSrv.create(Genre);
	}

	@PutMapping("/{id}")

	public Genre modifierGenre(@PathVariable Integer id,@RequestBody Genre Genre)
	{
		Genre.setId(id);
		return (Genre) genreSrv.update(Genre);
	}

	@DeleteMapping("/{id}")
	public void deleteGenre(@PathVariable Integer id)
	{
		genreSrv.deleteById(id);
	}


}
