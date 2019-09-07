import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PersonalAccountPageDto} from '../models/PersonalAccountPageDto';
import {Action} from '../models/Action';
import {Notification} from '../models/Notification';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  url = 'http://localhost:8080/lkz_project/account/';
  addActionUrl = 'http://localhost:8080/lkz_project/account/addAction';
  addNotificationUrl = 'http://localhost:8080/lkz_project/account/addNotification';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200'
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

  public addAction(action: Action) {
    return this.httpClient.post(this.addActionUrl, JSON.stringify(action), this.httpOptions);
  }


  public addNotification(notification: Notification) {
    return this.httpClient.post(this.addNotificationUrl, JSON.stringify(notification), this.httpOptions);
  }
}
