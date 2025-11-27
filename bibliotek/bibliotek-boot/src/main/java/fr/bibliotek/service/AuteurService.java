package fr.bibliotek.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.bibliotek.api.request.CreateOrUpdateAuteurRequest;
import fr.bibliotek.exception.AuteurNotFoundException;
import fr.bibliotek.model.Auteur;
import fr.bibliotek.repo.AuteurRepository;

@Service
public class AuteurService {

    private final static Logger log = LoggerFactory.getLogger(AuteurService.class);

    private final AuteurRepository repository;

    public AuteurService(AuteurRepository repository) {
        this.repository = repository;
    }

    public List<Auteur> findAll() {
        log.error("On recherche tous les auteurs v ia le service");
        return this.repository.findAll();
    }

    public Auteur findById(String id) {
        return this.repository.findById(id).orElseThrow(AuteurNotFoundException::new);
    }

    public Auteur save(CreateOrUpdateAuteurRequest request) {
        return this.save(new Auteur(), request);
    }

    public Auteur save(String id, CreateOrUpdateAuteurRequest request) {
        Auteur auteur = this.findById(id);

        return this.save(auteur, request);
    }

    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    private Auteur save(Auteur auteur, CreateOrUpdateAuteurRequest request) {
        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        return this.repository.save(auteur);
    }
}
