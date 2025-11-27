export class CollectionDto {
    constructor(private _id: string, private _nom: string) { }

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

    public toJson(): any {
        return {
            nom: this.nom
        };
    }
}
