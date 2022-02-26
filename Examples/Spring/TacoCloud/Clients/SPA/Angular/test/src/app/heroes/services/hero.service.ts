import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { MessageService } from 'src/app/messaging/services/message.service';
import { Hero } from '../common/hero';
import { HEROES } from '../hero-list/mock-heroes';

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  private heroesUrl = 'api/heroes';

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private httpClient: HttpClient,
    private messageService: MessageService
  ) { }

  getHeroes(): Observable<Hero[]>
  { 
    return this.getHeroesByCriteria();
  }

  getHero(id: number): Observable<Hero>
  {
    return this
            .httpClient
              .get<Hero>(`${this.heroesUrl}/${id}`)
                .pipe(
                  tap(_ => this.log(`fetched hero ${id}`)),
                  catchError(this.handleError<Hero>(`getHero id = ${id}`))
                );
  }

  findHeroesByNameTerm(term: string): Observable<Hero[]> {
    
    const searchCriteria = (term = term.trim()) ? `name=${term}` : '';

    return this.getHeroesByCriteria(searchCriteria);
  }

  private getHeroesByCriteria(criteria: string = '')
  {
    if (criteria) criteria = `?${criteria}`;
    
    return this
            .httpClient
              .get<Hero[]>(`${this.heroesUrl}/${criteria}`)
                .pipe(
                  tap(
                    (heroes: Hero[]) => heroes.length ?
                      this.log(`fetched ${heroes.length} heroes`) :
                      this.log('no one hero found')
                  ),
                  catchError(this.handleError<Hero[]>(`getHeroes(${criteria})`, []))
                );
  }

  addHero(hero: Hero): Observable<Hero> {
    
    return this
            .httpClient
              .post<Hero>(this.heroesUrl, hero, this.httpOptions)
                .pipe(
                  tap((newHero: Hero) => this.log(`added hero ${newHero.id}`)),
                  catchError(this.handleError<Hero>(`addHero name = '${hero.name}'`))
                );
  }

  updateHero(hero: Hero): Observable<any> {
    
    return this
            .httpClient.put(this.heroesUrl, hero, this.httpOptions)
              .pipe(
                tap(_ => this.log(`updated hero ${hero.id}`)),
                catchError(this.handleError<any>('updateHero'))
              );
  }

  deleteHero(id: number): Observable<Hero>
  {
    const heroDeleteUrl = `${this.heroesUrl}/${id}`;

    return this
            .httpClient
              .delete<Hero>(heroDeleteUrl, this.httpOptions)
                .pipe(
                    tap(_ => this.log(`deleted hero ${id}`)),
                    catchError(this.handleError<Hero>(`deleteHero id = ${id}`))
                );
  }

  private handleError<T>(operation: string, result?: T) {
    return (error: any): Observable<T> => {

      console.log(error);

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string)
  {
    this.messageService.add(`HeroService: ${message}`);
  }
}
