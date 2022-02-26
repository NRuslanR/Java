import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/app/messaging/services/message.service';
import { Hero } from '../common/hero';
import { HeroService } from '../services/hero.service';

@Component({
  selector: 'heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {

  heroes: Hero[] = []

  constructor(
    private heroService: HeroService
  ) { }

  ngOnInit(): void {

    this.getHeroes();

  }

  getHeroes(): void
  {
    this.heroService.getHeroes().subscribe(heroes => this.heroes = heroes);
  }

  addHero(name: string)
  {
    if (!(name = name.trim()))
    {
      console.log('Hero name is empty');
      return;
    }

    this.heroService.addHero({ name } as Hero).subscribe(hero => this.heroes.push(hero));
  }

  deleteHero(hero: Hero)
  {
    const deleterHeroFromList = (deletedHero: Hero) => {

      const targetHero = deletedHero ? deletedHero : hero;

      this.heroes = this.heroes.filter(h => h.id !== targetHero.id);

    };

    this.heroService.deleteHero(hero.id).subscribe(deleterHeroFromList);
  }
}
