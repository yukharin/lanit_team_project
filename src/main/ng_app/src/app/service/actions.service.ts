import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TheNotification4renderHtml} from "../model/input-output/TheNotification4renderHtml";
import {PaginationForm} from "../model/input-output/form/PaginationForm";
import {HttpUtil} from "./http-util";

@Injectable()
export class ActionsService {
  myhttp: HttpUtil = new HttpUtil();

  constructor(private http: HttpClient) { }

  public getRender(notificationId: string): Observable<TheNotification4renderHtml> {
    const url = this.myhttp.REST_URL + 'cabinet/the_notification/'
      + notificationId + '/theNotification4renderHtml';

    return this.http.get<TheNotification4renderHtml>(url, this.myhttp.httpOptionsEmptyGet);
  }

  public submitPagination(notificationId: string, newPaginationForm: PaginationForm): Observable<TheNotification4renderHtml> {
    const url = this.myhttp.REST_URL + 'cabinet/the_notification/'
      + notificationId + '/pagination';

    return this.http.post<TheNotification4renderHtml>(
      url, JSON.stringify(newPaginationForm), this.myhttp.httpOptionsJson
    );
  }

  public canAddAction(notificationId: string): Observable<boolean> {
    const url = this.myhttp.REST_URL + 'cabinet/the_notification/'
      + notificationId + '/add_action/canAdd';

    return this.http.get<boolean>(url, this.myhttp.httpOptionsEmptyGet);
  }
}
