package bibliotek.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibliotek.dto.request.CreateOrUpdateCollectionRequest;
import bibliotek.model.Collection;
import bibliotek.repo.CollectionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CollectionService {
    private final static Logger log = LoggerFactory.getLogger(CollectionService.class);

    private final CollectionRepository repository;

    public CollectionService(CollectionRepository repository) {
        this.repository = repository;
    }

    public Stream<Collection> findAll() {
        log.debug("Liste des collections");

        return this.repository.findAll().stream();
    }

    public Optional<Collection> findById(String id) {
        log.debug("Récupération de la collection {}", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Collection create(CreateOrUpdateCollectionRequest request) {
        log.debug("Création de la collection {}", request.getNom());

        Collection collection = new Collection();

        collection.setNom(request.getNom());

        this.repository.persist(collection);

        return collection;
    }

    @Transactional
    public Collection update(String id, CreateOrUpdateCollectionRequest request) {
        log.debug("Mise à jour de la collection {}", id);

        Collection collection = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        collection.setNom(request.getNom());

        this.repository.persist(collection);

        return collection;
    }

    @Transactional
    public boolean deleteById(String id) {
        log.debug("Suppression de la collection {}", id);

        try {
            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            log.error("Impossible de supprimer la collection {} : {}", id, ex.getMessage());
            return false;
        }
    }
}
