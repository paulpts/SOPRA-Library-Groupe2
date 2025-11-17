import { Routes } from '@angular/router';
import { EditeurPage } from './page/editeur-page/editeur-page';
import { Collection } from './page/collection-page/collection';
import { AuteurPage } from './page/auteur-page/auteur-page';
import { GenrePage } from './page/genre-page/genre-page';
import { LivrePage } from './page/livre-page/livre-page';

export const routes: Routes = [
    { path: 'editeur', component: EditeurPage },
    { path: 'collection', component: Collection },
    { path: 'auteur', component: AuteurPage },
    { path: 'genre', component: GenrePage },
    { path: 'livre', component: LivrePage }
];

