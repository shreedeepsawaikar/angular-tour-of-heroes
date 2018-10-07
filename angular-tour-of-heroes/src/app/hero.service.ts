import { MessageService } from './message.service';
import { Hero } from './hero';
import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {catchError, map, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
const baseURL = 'http://localhost:8080/api/heroes/';
@Injectable({
  providedIn: 'root'
})
export class HeroService {
  getHeros(): Observable<Hero[]> {
    this.messageService.add('HeroService: fetched heroes');
    return this.http.get<Hero[]>(baseURL)
    .pipe(
      tap(data => {
        console.log(data[0]);
      }),
      catchError(this.handleError('getHeroes', []))
    );
  }
  getHero(id: number): Observable<Hero> {
    return this.http.get<Hero>(`${baseURL}${id}`).pipe(
      tap(_ => this.log(`fetched hero id=${id}`)),
      catchError(this.handleError<Hero>(`getHero id=${id}`))
    );
  }
  updateHero(hero: Hero): Observable<any> {
    return this.http.put(`${baseURL}/update/`, hero, httpOptions).pipe(
      tap(_ => this.log(`updated hero id=${hero.id}`)),
      catchError(this.handleError<any>('updateHero'))
    );
  }
  addHero(heroName: string): Observable<Hero[]> {
    console.log(heroName);
    return this.http.post<Hero[]>(`${baseURL}/add/`, heroName).pipe(
      tap((hero: Hero) => this.log(`added hero w/ id=${hero.id}`)),
      catchError(this.handleError<Hero>('addHero'))
    );
  }
  deleteHero(id: number): Observable<Hero[]> {
    return this.http.delete<Hero[]>(`${baseURL}/delete/${id}`).pipe(
      tap(_ => this.log(`deleted hero id=${id}`)),
      catchError(this.handleError<Hero[]>('deleteHero'))
    );
  }

  // Search Hero
  searchHero(term: string): Observable<Hero[]> {
    console.log(term);
    return this.http.get<Hero[]>(`${baseURL}search/${term}`).pipe(
      tap(data => console.log(data)),
      catchError(this.handleError<any>('searchHero'))
    );
  }


  constructor(private http: HttpClient, private messageService: MessageService) {
  }
  /**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T> (operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
  /** Log a HeroService message with the MessageService */

}
private log(message: string) {
  this.messageService.add(`HeroService: ${message}`);
}

}
