import { NationaliteEnum } from "../enumerator/nationalite-enum";

export class EditeurDto {
    constructor(private _id: string, private _nom: string, private _pays: NationaliteEnum) { }

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

    public get pays(): NationaliteEnum {
        return this._pays;
    }

    public set pays(value: NationaliteEnum) {
        this._pays = value;
    }

    public toJson(): any {
        return {
            nom: this.nom,
            pays: this.pays
        };
    }
}
