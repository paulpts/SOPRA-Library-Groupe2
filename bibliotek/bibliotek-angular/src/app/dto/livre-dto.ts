export class LivreDto {
    constructor(
        private _id: string,
        private _nom: string,
        private _publication: string,
        private _auteurId?: string,
        private _auteurNom?: string,
        private _editeurId?: string,
        private _editeurNom?: string,
        private _collectionId?: string,
        private _collectionNom?: string
    ) { }

    public get id(): string {
        return this._id;
    }

    public set id(value: string) {
        this._id = value;
    }

    public get nom(): string {
        return this._nom;
    }

    public set nom(value: string) {
        this._nom = value;
    }

    public get publication(): string {
        return this._publication;
    }

    public set publication(value: string) {
        this._publication = value;
    }

    public get auteurId(): string | undefined {
        return this._auteurId;
    }

    public set auteurId(value: string) {
        this._auteurId = value;
    }

    public get auteurNom(): string | undefined {
        return this._auteurNom;
    }

    public set auteurNom(value: string) {
        this._auteurNom = value;
    }

    public get editeurId(): string | undefined {
        return this._editeurId;
    }

    public set editeurId(value: string) {
        this._editeurId = value;
    }

    public get editeurNom(): string | undefined {
        return this._editeurNom;
    }

    public set editeurNom(value: string) {
        this._editeurNom = value;
    }

    public get collectionId(): string | undefined {
        return this._collectionId;
    }

    public set collectionId(value: string) {
        this._collectionId = value;
    }

    public get collectionNom(): string | undefined {
        return this._collectionNom;
    }

    public set collectionNom(value: string) {
        this._collectionNom = value;
    }

    public toJson(): any {
        return {
            nom: this.nom,
            publication: this.publication,
            auteurId: this.auteurId,
            editeurId: this.editeurId,
            collectionId: this.collectionId
        };
    }
}
