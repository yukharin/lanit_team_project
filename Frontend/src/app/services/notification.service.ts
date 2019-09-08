import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Notification} from '../models/Notification';
import {Action} from "../models/Action";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  url = 'http://localhost:8080/lkz_project/api/notifications';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private httpClient: HttpClient) {
  }

  public deleteNotification(id: string): Observable<Response> {
    return this.httpClient.delete<Response>(this.url + '/' + id);
  }

  public getNotification(id: string): Observable<Notification> {
    return this.httpClient.get<Notification>(this.url + '/' + id);
  }

  public addNotification(notification: Notification) {
    return this.httpClient.post(this.url, JSON.stringify(notification), this.httpOptions);
  }

  public addAction(action: Action) {
    return this.httpClient.post(this.url + '/' + action.notification.id + '/actions', JSON.stringify(action), this.httpOptions);
  }

}
