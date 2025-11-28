package bibliotek.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibliotek.dto.request.CreateOrUpdateLivreRequest;
import bibliotek.model.Livre;
import bibliotek.repo.LivreRepository;
import bibliotek.repo.AuteurRepository;
import bibliotek.repo.CollectionRepository;
import bibliotek.repo.EditeurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivreService {

    private final static Logger log = LoggerFactory.getLogger(LivreService.class);
    private final LivreRepository livreRepository;
    private final AuteurRepository auteurRepository;
    private final EditeurRepository editeurRepository;
    private final CollectionRepository collectionRepository;

    public LivreService(LivreRepository livreRepository, AuteurRepository auteurRepository, EditeurRepository editeurRepository, CollectionRepository collectionRepository) {
        this.livreRepository = livreRepository;
        this.auteurRepository = auteurRepository;
        this.editeurRepository = editeurRepository;
        this.collectionRepository = collectionRepository;
    }

    public List<Livre> findAll() {
        log.debug("Liste des livres");
        return this.livreRepository.findAll().list();
    }

    public Optional<Livre> findById(String id) {
        log.debug("Récupération du livre {}", id);
        return this.livreRepository.findByIdOptional(id);
        
        
    }

    // Create
    @Transactional
    public Livre save(CreateOrUpdateLivreRequest request) {
         log.debug("Création du livre {}", request.getNom());
        return this.save(new Livre(), request);
    }

    // Update
    @Transactional
    public Livre save(String id, CreateOrUpdateLivreRequest request) {
        log.debug("Mise à jour du livre {} : {}", id,request.getNom());
        Livre livre = this.livreRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return this.save(livre, request);
    }

    @Transactional
    public boolean deleteById(String id) {
        log.debug("Suppression du livre {}", id);
        
        try {
            this.livreRepository.deleteById(id);
            return true;
            
        } catch (Exception e) {
            log.error("Impossible de supprimer le livre {} : {}", id,e.getMessage());
            return false;
        }
    }

    @Transactional
    private Livre save(Livre livre, CreateOrUpdateLivreRequest request) {
        livre.setNom(request.getNom());
        livre.setResume(request.getResume());
        livre.setPublication(request.getPublication());

        livre.setAuteur(this.auteurRepository.getReferenceById(request.getAuteurId()));
        livre.setEditeur(this.editeurRepository.getReferenceById(request.getEditeurId()));

        if (request.getCollectionId() != null && !request.getCollectionId().isBlank()) {
            livre.setCollection(this.collectionRepository.getReferenceById(request.getCollectionId()));
        }

        else {
            livre.setCollection(null);
        }

        // différenciation entre un Livre :

        //qui n'existe pas 
        if(livre.getId()==null){
            this.livreRepository.persist(livre); // persist ne renvoie rien , ça ne fait que save
        }

        // qui existe 
        return livre;
    }
}
