import { Routes } from '@angular/router';
import { EditeurPage } from './page/editeur/editeur-page/editeur-page';
import { Collection } from './page/collection-page/collection';
import { AuteurPage } from './page/auteur-page/page/auteur-page/auteur-page';
import { GenrePage } from './page/genre-page/genre-page';
import { LivrePage } from './page/livre-page/livre-page';

export const routes: Routes = [
  { path: 'editeur', component: EditeurPage }
];
export const routes: Routes = [
    { path: 'collection', component: Collection },
];
export const routes: Routes = [
    { path: 'auteur', component: AuteurPage}
];
export const routes: Routes = [
{ path: 'genre', component: GenrePage },
];
export const routes: Routes = [
    { path: 'livre', component: LivrePage}
];
