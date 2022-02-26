import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, Observable, Subject, switchMap } from 'rxjs';
import { Hero } from '../common/hero';
import { HeroService } from '../services/hero.service';

@Component({
  selector: 'heroes-search',
  templateUrl: './heroes-search.component.html',
  styleUrls: ['./heroes-search.component.css']
})
export class HeroesSearchComponent implements OnInit {

  heroes$?: Observable<Hero[]>;
  private searchTerms = new Subject<string>();
  
  constructor(private heroService: HeroService) { }

  search(heroName: string)
  {
    this.searchTerms.next(heroName);
  }

  ngOnInit(): void {

    this.heroes$ =
      this.searchTerms.pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap(term=> this.heroService.findHeroesByNameTerm(term))
      );
  }
}
