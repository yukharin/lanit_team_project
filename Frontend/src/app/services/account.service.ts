import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PersonalAccountPageDto} from '../models/PersonalAccountPageDto';
import {Action} from '../models/Action';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  url = 'http://localhost:8080/lkz_project/api/account/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private httpClient: HttpClient) {
  }

  public getPage(): Observable<PersonalAccountPageDto> {

    return this.httpClient.get<PersonalAccountPageDto>(this.url);
  }

  public getPageAndPass(personalAccount: PersonalAccountPageDto): Observable<PersonalAccountPageDto> {
    console.log('JSON!!: ' + JSON.stringify(personalAccount));
    return this.httpClient.post<PersonalAccountPageDto>(this.url, JSON.stringify(personalAccount), this.httpOptions);
  }

}
