import {Component, OnChanges, OnInit} from '@angular/core';
// import { HttpService} from './http.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TestUser} from '../../models/test/TestUser';
import {User} from '../../models/entity/User';
import {Notification} from '../../models/entity/Notification';
import {ActivatedRoute, Router} from '@angular/router';
import {FormArray, FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {FilterForm} from '../../models/input-output/form/FilterForm';
import {Cabinet4renderHtml} from '../../models/input-output/Cabinet4renderHtml';
import {PaginationForm} from '../../models/input-output/form/PaginationForm';
import {OrderByForm} from '../../models/input-output/form/OrderByForm';
import {TheNotification4renderHtml} from '../../models/input-output/TheNotification4renderHtml';

@Component({
  selector: 'app-root',
  templateUrl: './actions.html',
  styleUrls: ['./actions.css'],

  // providers: [HttpService]
})
export class Actions implements OnInit, OnChanges {

  // user: User;
  render: TheNotification4renderHtml;
  notificationId;
  httpOptions = {
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


  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router) { }
  // constructor(private httpService: HttpService) {}

  ngOnChanges() {
    console.log('Change');
  }
  ngOnInit() {

    // this.http.get<User>('http://localhost:8081/lkz_project_war_exploded/angular/cabinet/get_user') //todo notifications
    //   .subscribe((user) => {
    //     this.user = user;
    //     console.log(this.user);
    //   });


    this.notificationId = this.route.snapshot.paramMap.get('id');

    let body = new HttpParams();
    body = body.set('notificationId', this.notificationId.toString());
    const httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      params: body
    };

    this.http.get<TheNotification4renderHtml>('http://localhost:8081/lkz_project_war_exploded/angular/cabinet/the_notification/theNotification4renderHtml', httpOptions)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });

  }

  pageChanged(event) {
    this.newPaginationForm.page = event - 1;

    this.http.post<TheNotification4renderHtml>(
      'http://localhost:8081/lkz_project_war_exploded/angular/cabinet/the_notification/pagination?notificationId=' + this.notificationId, JSON.stringify(this.newPaginationForm), this.httpOptions)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }
  maxResultAply() {
    this.newPaginationForm.maxResult = this.maxResultForm.get('maxResult').value;
    this.http.post<TheNotification4renderHtml>(
      'http://localhost:8081/lkz_project_war_exploded/angular/cabinet/the_notification/pagination?notificationId=' + this.notificationId, JSON.stringify(this.newPaginationForm), this.httpOptions)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }
}
