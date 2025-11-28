package bibliotek.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class EntityNotFoundException extends WebApplicationException {

    public EntityNotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND);
    }
}
