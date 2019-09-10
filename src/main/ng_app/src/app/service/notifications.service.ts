import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TheNotification4renderHtml} from "../model/input-output/TheNotification4renderHtml";
import {PaginationForm} from "../model/input-output/form/PaginationForm";
import {Cabinet4renderHtml} from "../model/input-output/Cabinet4renderHtml";
import {FilterForm} from "../model/input-output/form/FilterForm";
import {OrderByForm} from "../model/input-output/form/OrderByForm";
import {HttpUtil} from "./http-util";

@Injectable()
export class NotificationsService {
  myhttp: HttpUtil = new HttpUtil();

  constructor(private http: HttpClient) { }

  public getRender(): Observable<Cabinet4renderHtml> {
    const url = this.myhttp.REST_URL + 'cabinet/'
      + 'cabinet4renderHtml';

    return this.http.get<Cabinet4renderHtml>(url, this.myhttp.httpOptionsEmptyGet);
  }

  public submitPagination(newPaginationForm: PaginationForm): Observable<Cabinet4renderHtml> {
    const url = this.myhttp.REST_URL + 'cabinet/'
      + 'pagination';

    return this.http.post<Cabinet4renderHtml>(
      url, JSON.stringify(newPaginationForm), this.myhttp.httpOptionsJson
    );
  }

  public submitFilter(newFilterForm: FilterForm): Observable<Cabinet4renderHtml> {
    const url = this.myhttp.REST_URL + 'cabinet/'
      + 'filters';

    return this.http.post<Cabinet4renderHtml>(
      url, JSON.stringify(newFilterForm), this.myhttp.httpOptionsJson
    );
  }

  public submitOrderBy(newOrderByForm: OrderByForm): Observable<Cabinet4renderHtml> {
    const url = this.myhttp.REST_URL + 'cabinet/'
      + 'orderby';

    return this.http.post<Cabinet4renderHtml>(
      url, JSON.stringify(newOrderByForm), this.myhttp.httpOptionsJson
    );
  }

}
