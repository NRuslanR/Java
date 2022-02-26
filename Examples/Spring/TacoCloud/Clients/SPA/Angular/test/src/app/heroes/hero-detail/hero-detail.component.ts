import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Hero } from '../common/hero';
import { HeroService } from '../services/hero.service';

@Component({
  selector: 'hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: ['./hero-detail.component.css']
})
export class HeroDetailComponent implements OnInit {

  hero?: Hero

  constructor(
    private heroService: HeroService,
    private route: ActivatedRoute,
    private location: Location
  ) { 

  }

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.getHero(id);

    console.log('ngOnInit');
  }

  getHero(id: number) {
    
    this.heroService.getHero(id).subscribe(hero => this.hero = hero);
  }

  saveInfo()
  {
    if (this.hero)
      this.heroService.updateHero(this.hero).subscribe(() => this.goBack());
  }

  goBack()
  {
    this.location.back();
  }
}
