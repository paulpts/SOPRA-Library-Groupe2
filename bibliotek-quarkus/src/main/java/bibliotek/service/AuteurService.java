package bibliotek.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import bibliotek.dto.request.CreateOrUpdateAuteurRequest;
import bibliotek.model.Auteur;
import bibliotek.repo.AuteurRepository;

@ApplicationScoped
public class AuteurService {

    private final static Logger log = LoggerFactory.getLogger(AuteurService.class);

    private final AuteurRepository repository;

    public AuteurService(AuteurRepository repository) {
        this.repository = repository;
    }

    public Stream<Auteur> findAll() {
        log.debug("Liste des auteurs");
        return this.repository.findAll().stream();
    }

    public Optional<Auteur> findById(String id) {
        log.debug("Récupération d'un auteur {}", id);
        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Auteur create(CreateOrUpdateAuteurRequest request) {
        log.debug("Création d'un auteur {}", request.getNom());

        Auteur auteur = new Auteur();
        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        this.repository.persist(auteur);
        return auteur;
    }

    @Transactional
    public Auteur update(String id, CreateOrUpdateAuteurRequest request) {
        log.debug("Mise à jour d'un auteur {}", id);

        Auteur auteur = this.repository.findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        return auteur;
    }

    @Transactional
    public boolean deleteById(String id) {
        log.debug("Suppression d'un auteur {}", id);

        try {
            return this.repository.deleteById(id);
        } catch (Exception ex) {
            log.error("Impossible de supprimer l'auteur {} : {}", id, ex.getMessage());
            return false;
        }
    }
}

