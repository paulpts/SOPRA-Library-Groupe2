package bibliotek.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

import bibliotek.dto.request.CreateOrUpdateEditeurRequest;
import bibliotek.dto.response.EditeurResponse;
import bibliotek.model.Editeur;
import bibliotek.service.EditeurService;

@Path("/editeur")
@Authenticated
public class EditeurResource {

    private final static Logger log = LoggerFactory.getLogger(EditeurResource.class);

    private final EditeurService service;

    public EditeurResource(EditeurService service) {
        this.service = service;
    }

    @GET
    @RolesAllowed({ "admin", "user" })
    public List<EditeurResponse> findAll() {
        log.debug("Lister les éditeurs");

        return this.service.findAll().map(EditeurResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    @RolesAllowed({ "admin", "user" })
    public Response findById(@PathParam("id") String id) {
        log.debug("Rechercher l'éditeur {}", id);

        Optional<Editeur> optEditeur = this.service.findById(id);

        if (optEditeur.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(EditeurResponse.convert(optEditeur.get())).build();
    }

    @POST
    @RolesAllowed("admin")
    public String create(@Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Créer l'éditeur {}", request.getNom());

        return this.service.create(request).getId();
    }

    @Path("/{id}")
    @PUT
    @RolesAllowed("admin")
    public String update(@PathParam("id") String id, @Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Mettre à jour l'éditeur {}", id);

        this.service.update(id, request);

        return id;
    }

    @Path("/{id}")
    @DELETE
    @RolesAllowed("admin")
    public boolean deleteById(@PathParam("id") String id) {
        log.debug("Supprimer l'éditeur {}", id);

        return this.service.deleteById(id);
    }
}