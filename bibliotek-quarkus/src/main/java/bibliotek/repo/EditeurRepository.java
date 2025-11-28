package bibliotek.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import bibliotek.model.Editeur;

@ApplicationScoped
public class EditeurRepository implements PanacheRepositoryBase<Editeur, String> {

}
