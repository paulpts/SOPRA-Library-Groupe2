import { Routes } from '@angular/router';
import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/utilisateur/login-page/login-page';
import { LivrePage } from './page/livre/livre-page/livre-page';
import { CollectionPage } from './page/collection/collection-page/collection-page';
import { EditeurPage } from './page/editeur/editeur-page/editeur-page';
import { authGuard } from './guard/auth-guard';
import { InscriptionPage } from './page/utilisateur/inscription-page/inscription-page';

export const routes: Routes = [
    { path: '', component: HomePage, canActivate: [ authGuard ] },
    { path: 'home', component: HomePage, canActivate: [ authGuard ] },
    { path: 'login', component: LoginPage },
    { path: 'inscription', component: InscriptionPage },
    { path: 'livre', component: LivrePage, canActivate: [ authGuard ] },
    { path: 'auteur', component: AuteurPage, canActivate: [ authGuard ] },
    { path: 'editeur', component: EditeurPage, canActivate: [ authGuard ] },
    { path: 'collection', component: CollectionPage, canActivate: [ authGuard ] },
];
