export class LivreDto {
    //attention au type date qui demande une conversion (recoit les données en string)
    constructor(
        private _id:number, 
        private _titre:string, 
        private _resumer : string, 
        private _annee : Date, 
        private _auteur : any,
        private _editeur: any,
        private _collection: any,
        private _genre: any
    ){}
    
    public get id(): number {
        return this._id;
    }
    
    public set id(value: number) {
        this._id = value;
    }
    
    
    public get titre(): string {
        return this._titre;
    }
    
    public set titre(value: string) {
        this._titre = value;
    }
    
    public get resumer(): string {
        return this._resumer;
    }
    
    public set resumer(value: string) {
        this._resumer = value;
    }

        public get annee(): Date {
        return this._annee;
    }
    
    public set annee(value: Date) {
        this._annee = value;
    }


        public get auteur(): any {
        return this._auteur;
    }
    
    public set auteur(value: any) {
        this._auteur = value;
    }


        public get editeur(): any {
        return this._editeur;
    }
    
    public set editeur(value: any) {
        this._editeur = value;
    }


        public get collection(): any {
        return this._collection;
    }
    
    public set collection(value: any) {
        this._collection = value;
    }


        public get genre(): any {
        return this._genre;
    }
    
    public set genre(value: any) {
        this._genre = value;
    }

    
    public toJson(): any{
        return{
            titre: this.titre,
            resumer: this.resumer,
            annee:this.annee,
            auteur: {"id":this.auteur},
            editeur: {"id":this.editeur},
            collection:{"id":this.collection},
            genre: {"id":this.genre}
        }
    }

}
