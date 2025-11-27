package fr.bibliotek.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.bibliotek.api.request.CreateOrUpdateCollectionRequest;
import fr.bibliotek.exception.CollectionNotFoundException;
import fr.bibliotek.model.Collection;
import fr.bibliotek.repo.CollectionRepository;

@Service
public class CollectionService {
    private final CollectionRepository repository;

    public CollectionService(CollectionRepository repository) {
        this.repository = repository;
    }

    public List<Collection> findAll() {
        return this.repository.findAll();
    }

    public Collection findById(String id) {
        return this.repository.findById(id).orElseThrow(CollectionNotFoundException::new);
    }

    public Collection save(CreateOrUpdateCollectionRequest request) {
        return this.save(new Collection(), request);
    }

    public Collection save(String id, CreateOrUpdateCollectionRequest request) {
        Collection collection = this.findById(id);

        return this.save(collection, request);
    }

    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    private Collection save(Collection collection, CreateOrUpdateCollectionRequest request) {
        collection.setNom(request.getNom());

        return this.repository.save(collection);
    }
}
