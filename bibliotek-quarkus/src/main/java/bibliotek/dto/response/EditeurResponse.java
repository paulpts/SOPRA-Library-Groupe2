package bibliotek.dto.response;

import bibliotek.enumerator.NationaliteEnum;
import bibliotek.model.Editeur;

public class EditeurResponse {

    private String id;
    private String nom;
    private NationaliteEnum pays;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NationaliteEnum getPays() {
        return pays;
    }

    public void setPays(NationaliteEnum pays) {
        this.pays = pays;
    }

    public static EditeurResponse convert(Editeur editeur) {
        EditeurResponse resp = new EditeurResponse();

        resp.setId(editeur.getId());
        resp.setNom(editeur.getNom());
        resp.setPays(editeur.getPays());

        return resp;
    }
}