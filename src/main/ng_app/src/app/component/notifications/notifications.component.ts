import {Component, OnChanges, OnInit} from '@angular/core';
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

@Component({
  selector: 'app-root',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.css'],
  // providers: [HttpService]
})
export class NotificationsComponent implements OnInit {

  render: Cabinet4renderHtml;

  filtersForm: FormGroup = new FormGroup({
      showArchive: new FormControl(false),
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

  constructor(
    // private httpService: HttpService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.http.get<Cabinet4renderHtml>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/cabinet4renderHtml')
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  filterAply() {
    this.newFilterForm.showArchive = this.filtersForm.get('showArchive').value;

    this.newFilterForm.idFilterStatus = new Array<number>();
    if ( this.filtersForm.get('_0').value ) this.newFilterForm.idFilterStatus.push(0)
    if ( this.filtersForm.get('_1').value ) this.newFilterForm.idFilterStatus.push(1)
    if ( this.filtersForm.get('_2').value ) this.newFilterForm.idFilterStatus.push(2)
    if ( this.filtersForm.get('_3').value ) this.newFilterForm.idFilterStatus.push(3)

    this.http.post<Cabinet4renderHtml>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/filters', JSON.stringify(this.newFilterForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  pageChanged(event) {
    this.newPaginationForm.page = event - 1;

    this.http.post<Cabinet4renderHtml>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/pagination', JSON.stringify(this.newPaginationForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  open(notification: Notification) {
    //todo remove '/'
    this.router.navigate(['/notifications/' + notification.id]);
  }

  maxResultAply() {
    this.newPaginationForm.maxResult = this.maxResultForm.get('maxResult').value;
    this.http.post<Cabinet4renderHtml>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/pagination', JSON.stringify(this.newPaginationForm), this.httpOptionsJson)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }
}
