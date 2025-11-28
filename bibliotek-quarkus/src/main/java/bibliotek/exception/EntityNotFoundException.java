package fr.bibliotek.exception;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

}
