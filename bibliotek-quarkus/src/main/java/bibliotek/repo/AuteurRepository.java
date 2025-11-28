package bibliotek.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import bibliotek.model.Auteur;

@ApplicationScoped
public class AuteurRepository implements PanacheRepositoryBase<Auteur, String>{
    
}
