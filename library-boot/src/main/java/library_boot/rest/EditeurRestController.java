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

import library_boot.model.Editeur;
import library_boot.service.EditeurService;
import library_boot.view.Views;

@RestController
@RequestMapping("/api/editeur")
@CrossOrigin("*")
public class EditeurRestController {

    @Autowired
    EditeurService editeurSrv;

    @JsonView(Views.Editeur.class)
    @GetMapping
    public List<Editeur> findAll() {
        return editeurSrv.getAll();
    }

    @JsonView(Views.Editeur.class)
    @GetMapping("/{id}")
    public Editeur findById(@PathVariable Integer id) {
        return editeurSrv.getById(id);
    }

    @JsonView(Views.Editeur.class)
    @PostMapping
    public Editeur ajoutEditeur(@RequestBody Editeur editeur) {
        
        
        return editeurSrv.create(editeur);
    }

    @JsonView(Views.Editeur.class)
    @PutMapping("/{id}")
    public Editeur modifierEditeur(@PathVariable Integer id, @RequestBody Editeur editeur) {
        editeur.setId(id); 
        return (Editeur)editeurSrv.update(editeur);
    }

   
    @DeleteMapping("/{id}")
    public void supprimerEditeur(@PathVariable Integer id) {
        editeurSrv.deleteById(id);
    }
}