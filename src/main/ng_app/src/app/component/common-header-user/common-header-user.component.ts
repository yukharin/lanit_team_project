import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
// import { ActionsService} from './http.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../model/entity/User';
import {Notification} from '../../model/entity/Notification';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder} from "@angular/forms";
import {HeaderUserService} from "../../service/header-user.service";

@Component({
  selector: 'app-common-header-user',
  // selector: 'app-root',
  templateUrl: './common-header-user.component.html',
  styleUrls: ['./common-header-user.css'],
  providers: [HeaderUserService]
})
export class CommonHeaderUserComponent implements OnInit {
  user: User;

  constructor(
    private headerUserService: HeaderUserService,
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.headerUserService.getUser()
      .subscribe((user) => {
        this.user = user;
        console.log(this.user);
      });
  }
}
