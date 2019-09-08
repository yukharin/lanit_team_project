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

@Component({
  selector: 'app-root',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.css'],

  // providers: [HttpService]
})
export class NotificationsComponent implements OnInit, OnChanges {

  // notification: Notification;
  // notifications: Notification[];
  // user: User;
  render: Cabinet4renderHtml;
  // response: any;
  // list: string[];

  filtersForm: FormGroup = new FormGroup({
      showArchive: new FormControl(false)
    ,
      // idFilterStatus: new FormControl()
      // idFilterStatus: new FormArray([])
      _0: new FormControl(true),
      _1: new FormControl(true),
      _2: new FormControl(true),
      _3: new FormControl(true),
    });

  maxResultForm: FormGroup = new FormGroup({
    maxResult: new FormControl(10) //todo replace
    });

  newFilterForm: FilterForm = new FilterForm();
  newPaginationForm: PaginationForm = new PaginationForm();
  newOrderByForm: OrderByForm = new OrderByForm();

  httpOptionsJson = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder
  ) { }
  // constructor(private httpService: HttpService) {}

  ngOnChanges() {
    console.log('Change');
  }
  ngOnInit() {
    // const userId = this.route.snapshot.paramMap.get('userId');

    // this.http.get<Notification[]>('http://localhost:8081/lkz_project_war_exploded/angular/user_notifications?userId=' + userId)
    // .subscribe((notifications) => {
    //   this.notifications = notifications;
    //   console.log(this.notifications);
    // });


    // this.http.get<User>('http://localhost:8081/lkz_project_war_exploded/angular/cabinet/get_user?id=' + userId)
    //   .subscribe((user) => {
    //     this.user = user;
    //     console.log(this.user);
    //   });
    // this.http.get<Cabinet4renderHtml>('http://localhost:8081/lkz_project_war_exploded/angular/cabinet/cabinet4renderHtml?userId=' + userId)
    //   .subscribe((render) => {
    //     this.render = render;
    //     console.log(this.render);
    //   });
    // this.http.get<User>('http://localhost:8081/lkz_project_war_exploded/angular/cabinet/get_user')
    //   .subscribe((user) => {
    //     this.user = user;
    //     console.log(this.user);
    //   });
    this.http.get<Cabinet4renderHtml>('http://localhost:8081/lkz_project_war_exploded/angular/cabinet/cabinet4renderHtml')
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });

    // this.filtersForm = this.formBuilder.group({
    //   showArchive: this.render.state.filterForm.showArchive,
    //   // idFilterStatus: this.render.state.filterForm.idFilterStatus,
    // });
    // for ()
    // if ( this.render.newCheckedMainListNotificStatusesId == 0 ) this.filtersForm._1 = new FormControl(trua);
    // if ( this.filtersForm.get('_1').value ) this.newFilterForm.idFilterStatus.push(1)
    // if ( this.filtersForm.get('_2').value ) this.newFilterForm.idFilterStatus.push(2)
    // if ( this.filtersForm.get('_3').value ) this.newFilterForm.idFilterStatus.push(3)
    // this.filtersForm.get('_0').value = this.render.newCheckedMainListNotificStatusesId;

  }

  filterAply() {
    this.newFilterForm.showArchive = this.filtersForm.get('showArchive').value;

    // this.newfilter.idFilterStatus = this.filtersForm.get('idFilterStatus').value;
    // console.log('idFilterStatus');
    // console.log(this.newfilter.idFilterStatus);

    this.newFilterForm.idFilterStatus = new Array<number>();
    if ( this.filtersForm.get('_0').value ) this.newFilterForm.idFilterStatus.push(0)
    if ( this.filtersForm.get('_1').value ) this.newFilterForm.idFilterStatus.push(1)
    if ( this.filtersForm.get('_2').value ) this.newFilterForm.idFilterStatus.push(2)
    if ( this.filtersForm.get('_3').value ) this.newFilterForm.idFilterStatus.push(3)
    //
    // this.newfilter.idFilterStatus = new Array(
    //   this.filtersForm.get('NEW').value,
    //   this.filtersForm.get('IN_WORK').value,
    //   this.filtersForm.get('REJECTED').value,
    //   this.filtersForm.get('OK').value,
    // );


    // this.filter.idFilterStatus = this.filtersForm.controls.idFilterStatus.value;
    // console.log(this.filtersForm.controls.idFilterStatus.value);

    // this.http.post<FilterForm>(
    //   'http://localhost:8081/lkz_project_war_exploded/angular/filters', JSON.stringify(this.render.state.filterForm), this.httpOptions)
    //   .subscribe((str) => {
    //     this.response = str;
    //     console.log(this.response);
    //   });
    // if (this.response == "OK") {
    //   this.http.get<any>('http://localhost:8081/lkz_project_war_exploded/angular/cabinet4renderHtml?userId=' + this.user.id)
    //     .subscribe((render) => {
    //       this.render = render;
    //       console.log(this.render);
    //     });
    // } else {
    //   console.log('ERROR NO EDIT' + this.render);
    //   alert('ERROR NO EDIT' + this.render);
    // }

    this.http.post<Cabinet4renderHtml>(
      // 'http://localhost:8081/lkz_project_war_exploded/angular/filters', JSON.stringify(this.render.state.filterForm), this.httpOptions)
      'http://localhost:8081/lkz_project_war_exploded/angular/cabinet/filters', JSON.stringify(this.newFilterForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  pageChanged(event) {
    this.newPaginationForm.page = event - 1;

    this.http.post<Cabinet4renderHtml>(
      'http://localhost:8081/lkz_project_war_exploded/angular/cabinet/pagination', JSON.stringify(this.newPaginationForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  open(notification: Notification) {
    // this.router.navigate(['/notifications'], {queryParams: {'id': notification.id} });
    this.router.navigate(['/notifications/' + notification.id]);
  }

  maxResultAply() {
    this.newPaginationForm.maxResult = this.maxResultForm.get('maxResult').value;
    this.http.post<Cabinet4renderHtml>(
      'http://localhost:8081/lkz_project_war_exploded/angular/cabinet/pagination', JSON.stringify(this.newPaginationForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }
}
