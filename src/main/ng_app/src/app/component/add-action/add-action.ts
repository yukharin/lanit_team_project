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
import {AddAction4renderHtml} from '../../model/input-output/AddAction4renderHtml';
import {ActionPortionDto} from '../../model/input-output/dto.valid/ActionPortionDto';
import {Location} from '@angular/common';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './add-action.html',
  styleUrls: ['./add-action.css'],
  // providers: [HttpService]
})
export class AddAction implements OnInit {

  notificationId: string;
  render: AddAction4renderHtml;
  actionPortionDto: ActionPortionDto = new ActionPortionDto();
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
    private location: Location) { }

  ngOnInit() {
    this.notificationId = this.route.snapshot.paramMap.get('id');

    // let body = new HttpParams();
    // body = body.set('notificationId', this.notificationId.toString());
    const httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      // params: body
    };

    this.http.get<AddAction4renderHtml>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/the_notification/' + this.notificationId + '/add_action/addAction4renderHtml',
      httpOptions)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  addActionAply() {
    this.actionPortionDto.notificationId = this.render.currentNotification.id;

    this.http.post<boolean>(
      'http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/cabinet/the_notification/' + this.notificationId + '/add_action/save',
      JSON.stringify(this.actionPortionDto),
      this.httpOptionsJson)
      .subscribe((hasSaved) => {
        if (hasSaved) {
          // this.router.navigate(['..']);
          // this.location.back();
          this.router.navigate(['notifications/' + this.notificationId]);//todo replace
        }
        else {
          alert("DONT SAVE !!!");
        }
      });
  }
}
