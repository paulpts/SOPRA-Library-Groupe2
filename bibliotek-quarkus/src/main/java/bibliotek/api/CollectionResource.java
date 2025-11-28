package bibliotek.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bibliotek.dto.request.CreateOrUpdateCollectionRequest;
import bibliotek.dto.response.CollectionResponse;
import bibliotek.model.Collection;
import bibliotek.service.CollectionService;
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


@Path("/collection")
@Authenticated
public class CollectionResource {
    private final static Logger log = LoggerFactory.getLogger(CollectionResource.class);

    private final CollectionService service;

    public CollectionResource(CollectionService service) {
        this.service = service;
    }

    @GET
    public List<CollectionResponse> findAll() {
        log.debug("Lister les collections");

        return this.service.findAll().map(CollectionResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") int id) {
        log.debug("Rechercher la collection {}", id);

        Optional<Collection> optCollection = this.service.findById(id);

        if (optCollection.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(CollectionResponse.convert(optCollection.get())).build();
    }

    @POST
    public int create(@Valid CreateOrUpdateCollectionRequest request) {
        log.debug("Créer la collection {}", request.getLibelle());

        return this.service.create(request).getId();
    }

    @Path("/{id}")
    @PUT
    public int update(@PathParam("id") int id, @Valid CreateOrUpdateCollectionRequest request) {
        log.debug("Mettre à jour la collection {}", id);

        this.service.update(id, request);

        return id;
    }

    @Path("/{id}")
    @DELETE
    public boolean deleteById(@PathParam("id") int id) {
        log.debug("Supprimer la collection {}", id);

        return this.service.deleteById(id);
    }
}
