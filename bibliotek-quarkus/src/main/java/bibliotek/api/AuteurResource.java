package bibliotek.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibliotek.service.AuteurService;
import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import bibliotek.dto.response.AuteurResponse;
import bibliotek.model.Auteur;
import bibliotek.dto.request.CreateOrUpdateAuteurRequest;



@Path("/auteur")
//@Authenticated
public class AuteurResource {
    private final static Logger log = LoggerFactory.getLogger(AuteurResource.class);

    private final AuteurService service;

    public AuteurResource(AuteurService service) {
        this.service = service;
    }

    @GET
    public List<AuteurResponse> findAll() {
        log.debug("Lister les auteurs");

        return this.service.findAll().map(AuteurResponse::convert).toList();    
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") String id) {
        log.debug("Rechercher l'auteur {}", id);

        Optional<Auteur> optAuteur = this.service.findById(id);

        if (optAuteur.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(AuteurResponse.convert(optAuteur.get())).build();
    }

    @POST
    public String create(@Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Créer un auteur {}", request.getNom());

        return this.service.create(request).getId();
    }

    @Path("/{id}")
    @PUT
    public String update(@PathParam("id") String id, @Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Mettre à jour un auteur {}", id);

        this.service.update(id, request);

        return id;
    }

    @Path("/{id}")
    @DELETE
    public boolean deleteById(@PathParam("id") String id) {
        log.debug("Supprimer l'auteur {}", id);

        return this.service.deleteById(id);
    }
    
}
