package bibliotek.dto.response;

import bibliotek.model.Collection;

public class CollectionResponse {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static CollectionResponse convert(Collection collection) {
        CollectionResponse resp = new CollectionResponse();

        resp.setId(collection.getId());
        resp.setNom(collection.getNom());

        return resp;
    }
}
