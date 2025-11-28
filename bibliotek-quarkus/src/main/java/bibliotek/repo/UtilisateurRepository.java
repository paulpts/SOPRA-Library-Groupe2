package bibliotek.repo;

import java.util.Optional;

import bibliotek.model.Utilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtilisateurRepository implements PanacheRepositoryBase<Utilisateur, String> {

    public Optional<Utilisateur> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
}
