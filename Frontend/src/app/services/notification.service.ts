import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Notification} from '../models/Notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  url = 'http://localhost:8080/lkz_project/account/notifications';

  constructor(private httpClient: HttpClient) {
  }

  deleteNotification(id: string): Observable<Response> {
    return this.httpClient.delete<Response>(this.url + '/' + id);
  }

  getNotification(id: string): Observable<Notification> {
    return this.httpClient.get<Notification>(this.url + '/' + id);
  }

}
