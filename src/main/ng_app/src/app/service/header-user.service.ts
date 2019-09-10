import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TheNotification4renderHtml} from "../model/input-output/TheNotification4renderHtml";
import {PaginationForm} from "../model/input-output/form/PaginationForm";
import {HttpUtil} from "./http-util";
import {User} from "../model/entity/User";

@Injectable()
export class HeaderUserService {
  myhttp: HttpUtil = new HttpUtil();

  constructor(private http: HttpClient) {
  }

  public getUser(): Observable<User> {
    const url = this.myhttp.REST_URL + 'service/getUser';

    return this.http.get<User>(url);
  }
}
