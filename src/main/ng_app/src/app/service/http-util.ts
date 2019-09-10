import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {TheNotification4renderHtml} from "../model/input-output/TheNotification4renderHtml";
import {PaginationForm} from "../model/input-output/form/PaginationForm";

@Injectable()
export class HttpUtil {
  BASE_ROOT_URL = 'http://localhost:8080/lkz_project-1.0-SNAPSHOT/';
  REST_URL = this.BASE_ROOT_URL +'angular/';

  httpOptionsEmptyGet = {
    headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
    // params: body
  };
  httpOptionsJson = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  // let body = new HttpParams();
  // body = body.set('notificationId', this.notificationId.toString());
  // const httpOptionsGet = {
  //   headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
  // params: body
  // };

    // getData() : Observable<User[]> {
    //     return this.http.get('list-users.json') // Observable<any>
    //                         .pipe(map(data => {
    //                             const usersList = data["userList"];
    //                           // tslint:disable-next-line:only-arrow-functions
    //                             return usersList.map(function(user: any) {
    //                                 return {name: user.userName, age: user.userAge};
    //                             });
    //                         }));
    //
    // }
}
