package bibliotek.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibliotek.dto.request.CreateOrUpdateLivreRequest;
import bibliotek.dto.response.EntityCreatedResponse;
import bibliotek.dto.response.EntityUpdatedResponse;
import bibliotek.dto.response.LivreResponse;
import bibliotek.model.Livre;
import bibliotek.service.LivreService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/livre")
@Authenticated
public class LivreResource {
    
    private final static Logger log = LoggerFactory.getLogger(LivreService.class);
    
    private final LivreService service;
    
    public LivreResource(LivreService service) {
        this.service = service;
    }
    
    @GET
    @RolesAllowed({ "admin", "user" })
    public List<LivreResponse> findAll() {
        log.debug("Lister les livres");
        return this.service.findAll().stream().map(LivreResponse::convert).toList();
    }
    
    @GET
    @Path("/{id}")
    @RolesAllowed({ "admin", "user" })
    public Response findById(@PathParam("id") String id) {
        log.debug("Rechercher le livre {}", id);
        
        Optional<Livre> optLivre = this.service.findById(id);
        
        if(optLivre.isEmpty()){
            return Response.status(Status.NOT_FOUND).build();
        }
        
        return Response.ok(LivreResponse.convert(optLivre.get())).build();
    }
    
    @POST
    @RolesAllowed("admin")
    public Response create(@Valid CreateOrUpdateLivreRequest request) {
        log.debug("Créer le livre {}", request.getNom());
        EntityCreatedResponse response =  new EntityCreatedResponse(this.service.save(request).getId());
        // .entity(response) renvoie l'EntityCreatedResponse qui a été crée
        return Response.status(Status.CREATED).entity(response).build();
    }
    
    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public EntityUpdatedResponse update(@PathParam("id") String id, @Valid CreateOrUpdateLivreRequest request) {
        log.debug("Modifier le livre {}", request.getNom());
        this.service.save(id, request);
        
        return new EntityUpdatedResponse(id, true);
    }
    
    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public boolean deleteById(@PathParam("id") String id) {
        log.debug("Supprimer le livre {}", id);

        return this.service.deleteById(id);
        // return Response.noContent().build();
    }
}
