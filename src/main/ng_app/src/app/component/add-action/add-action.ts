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
import {AddAction4renderHtml} from '../../model/input-output/AddAction4renderHtml';
import {ActionPortionDto} from '../../model/input-output/dto.valid/ActionPortionDto';
import {Location} from '@angular/common';
import {NgForm} from '@angular/forms';
import {HttpUtil} from "../../service/http-util";
import {ActionsService} from "../../service/actions.service";
import {AddActionService} from "../../service/add-action.service";

@Component({
  selector: 'app-root',
  templateUrl: './add-action.html',
  styleUrls: ['./add-action.css'],
  providers: [AddActionService]
})
export class AddAction implements OnInit {

  notificationId: string;
  render: AddAction4renderHtml;
  actionPortionDto: ActionPortionDto = new ActionPortionDto();

  constructor(
    private addActionService: AddActionService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location) { }

  ngOnInit() {
    this.notificationId = this.route.snapshot.paramMap.get('id');

    this.addActionService.getRender(this.notificationId)
      .subscribe((render) => {
        this.render = render;
        console.log(this.render);
      });
  }

  addActionAply() {
    this.actionPortionDto.notificationId = this.render.currentNotification.id;

    this.addActionService.addAction(this.notificationId, this.actionPortionDto)
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
