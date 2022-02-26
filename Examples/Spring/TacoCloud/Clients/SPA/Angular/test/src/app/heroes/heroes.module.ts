import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeroesComponent } from './hero-list/heroes.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryHeroesDataService } from './services/in-memory-heroes-data-service.service';
import { HeroesSearchComponent } from './heroes-search/heroes-search.component';

@NgModule({
  declarations: [
    HeroesComponent,
    HeroDetailComponent,
    HeroesSearchComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryHeroesDataService,
      {
        dataEncapsulation: false
      }
    )
  ],
  exports: [
    HeroesComponent,
    HeroDetailComponent,
    HeroesSearchComponent
  ]
})
export class HeroesModule { }
