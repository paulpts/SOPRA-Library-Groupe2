package bibliotek.api;

import bibliotek.dto.request.AuthRequest;
import bibliotek.dto.response.AuthResponse;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.jpa.runtime.JpaIdentityProvider;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthResource {

    @Inject
    private JpaIdentityProvider identityProvider;

    @Inject
    private EntityManager entityManager;

    @POST
    //@Path("/auth")
    public AuthResponse auth(AuthRequest request) {
        UsernamePasswordAuthenticationRequest authenticationRequest = new UsernamePasswordAuthenticationRequest(
                request.getUsername(),
                new PasswordCredential(request.getPassword().toCharArray()));
        SecurityIdentity identity = this.identityProvider.authenticate(this.entityManager, authenticationRequest);
        String jwt = Jwt.issuer("bibliotek-quarkus-issuer")
                .upn(request.getUsername()) // User Principal Name
                .groups(identity.getRoles()) // Le ou les rôles
                .sign() // On signe le jeton avec la clé privée
        ;
        return new AuthResponse(true,jwt);
    }

}
