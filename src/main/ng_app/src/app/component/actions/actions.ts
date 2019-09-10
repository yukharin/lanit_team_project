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
import {TheNotification4renderHtml} from '../../model/input-output/TheNotification4renderHtml';
import {ActionsService} from "../../service/actions.service";

@Component({
  selector: 'app-root',
  templateUrl: './actions.html',
  styleUrls: ['./actions.css'],
  providers: [ActionsService]
})
export class Actions implements OnInit {

  render: TheNotification4renderHtml;
  notificationId;

  maxResultForm: FormGroup = new FormGroup({
    maxResult: new FormControl(10) //todo replace
  });

  newFilterForm: FilterForm = new FilterForm();
  newPaginationForm: PaginationForm = new PaginationForm();
  newOrderByForm: OrderByForm = new OrderByForm();

  constructor(
    private actionService: ActionsService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.notificationId = this.route.snapshot.paramMap.get('id');

    this.actionService.getRender(this.notificationId)
      .subscribe((render) => {
        this.render = render;
        // console.log(this.render);
      });
  }

  pageChanged(event) {
    this.newPaginationForm.page = event - 1;
    this.submitPagination();
  }
  maxResultAply() {
    this.newPaginationForm.maxResult = this.maxResultForm.get('maxResult').value;
    this.submitPagination();
  }
  private submitPagination() {
    this.actionService.submitPagination(this.notificationId, this.newPaginationForm)
      .subscribe((render) => {
        this.render = render;
        // console.log(this.render);
      });
  }

  add_action() {
    this.actionService.canAddAction(this.notificationId)
      .subscribe((canBeAdd) => {
        if(canBeAdd) {this.router.navigate([
          // 'add_action'
          'notifications/' + this.notificationId + '/add_action'
        ]);}
        else { alert("Уведомление находится уже в статусе Одобренного или Отклоненого");}
      });
  }

}
