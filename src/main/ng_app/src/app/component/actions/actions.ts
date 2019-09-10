import {Component, OnChanges, OnInit} from '@angular/core';
// import { HttpService} from './http.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../model/entity/User';
import {Notification} from '../../model/entity/Notification';
import {ActivatedRoute, Router} from '@angular/router';
import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {FilterForm} from '../../model/input-output/form/FilterForm';
import {Cabinet4renderHtml} from '../../model/input-output/Cabinet4renderHtml';
import {PaginationForm} from '../../model/input-output/form/PaginationForm';
import {OrderByForm} from '../../model/input-output/form/OrderByForm';
import {TheNotification4renderHtml} from '../../model/input-output/TheNotification4renderHtml';

@Component({
  selector: 'app-root',
  templateUrl: './actions.html',
  styleUrls: ['./actions.css'],
  // providers: [HttpService]
})
export class Actions implements OnInit {

  render: TheNotification4renderHtml;
  notificationId;
  httpOptionsJson = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  maxResultForm: FormGroup = new FormGroup({
    maxResult: new FormControl(10) //todo replace
  });

  newFilterForm: FilterForm = new FilterForm();
  newPaginationForm: PaginationForm = new PaginationForm();
  newOrderByForm: OrderByForm = new OrderByForm();


  constructor(
    // private httpService: HttpService
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.notificationId = this.route.snapshot.paramMap.get('id');

    let body = new HttpParams();
    body = body.set('notificationId', this.notificationId.toString());
    const httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      params: body
    };
    this.http.get<TheNotification4renderHtml>('http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/the_notification/theNotification4renderHtml',
      httpOptions)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });

  }

  pageChanged(event) {
    this.newPaginationForm.page = event - 1;

    this.http.post<TheNotification4renderHtml>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/the_notification/pagination?notificationId=' + this.notificationId,
      JSON.stringify(this.newPaginationForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  maxResultAply() {
    this.newPaginationForm.maxResult = this.maxResultForm.get('maxResult').value;
    this.http.post<TheNotification4renderHtml>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/the_notification/pagination?notificationId=' + this.notificationId,
      JSON.stringify(this.newPaginationForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  add_action() {
    this.notificationId = this.route.snapshot.paramMap.get('id');

    let body = new HttpParams();
    body = body.set('notificationId', this.notificationId.toString());
    const httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      params: body
    };
    // TODO replace get in post
    this.http.get<boolean>('http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/the_notification/add_action/canBeAdd',
      httpOptions)
      .subscribe((canBeAdd) => {
        console.log(canBeAdd);

        if(canBeAdd) {this.router.navigate([
          // 'add_action'
          'notifications/' + this.notificationId + '/add_action'
        ]);}
        else { alert("Уведомление находится уже в статусе Одобренного или Отклоненого");}
      });

  }

}
