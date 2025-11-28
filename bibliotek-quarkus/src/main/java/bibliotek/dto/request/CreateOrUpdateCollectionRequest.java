package bibliotek.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateCollectionRequest {
    @NotBlank
    private String nom;

    public String getLibelle() {
        return nom;
    }

	public CreateOrUpdateCollectionRequest(@NotBlank String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
