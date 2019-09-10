import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TheNotification4renderHtml} from "../model/input-output/TheNotification4renderHtml";
import {PaginationForm} from "../model/input-output/form/PaginationForm";
import {HttpUtil} from "./http-util";
import {AddAction4renderHtml} from "../model/input-output/AddAction4renderHtml";
import {ActionPortionDto} from "../model/input-output/dto.valid/ActionPortionDto";

@Injectable()
export class AddActionService {
  myhttp: HttpUtil = new HttpUtil();

  constructor(private http: HttpClient) { }

  public getRender(notificationId: string): Observable<AddAction4renderHtml> {
    const url = this.myhttp.REST_URL + 'cabinet/the_notification/'
      + notificationId + '/add_action/addAction4renderHtml';

    return this.http.get<AddAction4renderHtml>(url, this.myhttp.httpOptionsEmptyGet);
  }

  public addAction(notificationId: string, actionPortionDto: ActionPortionDto): Observable<boolean> {
    const url = this.myhttp.REST_URL + 'cabinet/the_notification/'
      + notificationId + '/add_action/save';

    return this.http.post<boolean>(url, actionPortionDto, this.myhttp.httpOptionsJson);
  }
}
