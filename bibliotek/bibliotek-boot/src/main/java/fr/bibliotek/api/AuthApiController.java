package fr.bibliotek.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bibliotek.api.request.AuthRequest;
import fr.bibliotek.api.response.AuthResponse;
import fr.bibliotek.security.service.SecurityService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthApiController {
    private final SecurityService service;

    public AuthApiController(SecurityService service) {
        this.service = service;
    }

    @PostMapping
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        return this.service.auth(request);
    }
}
