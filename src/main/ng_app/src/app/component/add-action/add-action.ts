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
import {AddAction4renderHtml} from '../../models/input-output/AddAction4renderHtml';
import {ActionPortionDto} from '../../models/input-output/dto.valid/ActionPortionDto';
import {Location} from '@angular/common';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './add-action.html',
  styleUrls: ['./add-action.css'],

  // providers: [HttpService]
})
export class AddAction implements OnInit, OnChanges{

  notificationId: string;
  render: AddAction4renderHtml;
  actionPortionDto: ActionPortionDto = new ActionPortionDto();
  httpOptionsJson = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  // addActionForm: FormGroup = new FormGroup({//todo replace
  //   // notificationId: new FormControl(),
  //   idUserImplementor: new FormControl(),
  //   idActionType: new FormControl(),
  //   idNotificationStatus: new FormControl(),
  //   content: new FormControl()
  // });

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router, private location: Location) { }
  // constructor(private httpService: HttpService) {}

  ngOnChanges() {
    console.log('Change');
  }
  ngOnInit() {
    this.notificationId = this.route.snapshot.paramMap.get('id');

    let body = new HttpParams();
    body = body.set('notificationId', this.notificationId.toString());
    const httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
      params: body
    };

    this.http.get<AddAction4renderHtml>(
      'http://localhost:8081/lkz_project_war_exploded/angular/cabinet/the_notification/add_action/addAction4renderHtml',
      httpOptions)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  addActionAply() {
    // this.notificationId = this.route.snapshot.paramMap.get('id');
    // this.actionPortionDto.notificationId = Number( this.notificationId );
    this.actionPortionDto.notificationId = this.render.currentNotification.id;

    // console.log('------------------------------')
    // console.log(this.addActionForm.get('notificationId').value);
    // console.log(this.addActionForm.controls.notificationId.value);
    // console.log('------------------------------')
    // console.log(this.addActionForm.get('idUserImplementor').value);
    // console.log(this.addActionForm.controls.idUserImplementor.value);
    // console.log('------------------------------')

    // console.log('------------------------------')
    // console.log(this.notificationId );
    // console.log( Number( this.notificationId ) );
    // // this.actionPortionDto.notificationId = Number( this.addActionForm.get('notificationId').value );
    //
    // this.actionPortionDto.idUserImplementor = this.addActionForm.get('idUserImplementor').value;
    // this.actionPortionDto.idActionType = this.addActionForm.get('idActionType').value;
    // this.actionPortionDto.idNotificationStatus = this.addActionForm.get('idNotificationStatus').value;
    // this.actionPortionDto.content = this.addActionForm.get('content').value;

    this.http.post<boolean>(
      'http://localhost:8081/lkz_project_war_exploded/angular/cabinet/the_notification/add_action/save',
      JSON.stringify(this.actionPortionDto),
      this.httpOptionsJson)
      .subscribe((hasSaved) => {
        if (hasSaved) {
          // this.router.navigate(['..']);
          // this.location.back();
          this.router.navigate(['notifications/' + this.notificationId]); //todo replace
        }
        else {
          // useValidateForm
          alert("DONT SAVE !!!");
        }
      });
  }
}
