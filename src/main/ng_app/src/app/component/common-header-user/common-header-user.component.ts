import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
// import { HttpService} from './http.service';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TestUser} from '../../models/test/TestUser';
import {User} from '../../models/entity/User';
import {Notification} from '../../models/entity/Notification';

@Component({
  selector: 'app-common-header-user',
  // selector: 'app-root',
  templateUrl: './common-header-user.component.html',
  styleUrls: ['./common-header-user.css'],
  // providers: [HttpService]
})
export class CommonHeaderUserComponent implements OnInit, OnChanges {

  // @Input()
  user: User;

  // @Input()
  // setUser(user: User): void {
  //     this.user = user;
  // }
  // get user() { return this._user; }

  // @Output() userNameChange = new EventEmitter<User>();
  //
  // onNameChange(model: User) {
  //   this.user = model;
  //   this.userNameChange.emit(model);
  // }
  // response: any;

  constructor(private http: HttpClient) { }
  // constructor(private httpService: HttpService) {}

  ngOnInit() {
    this.extracted();
  }
  ngOnChanges() {
    // this.extracted();
    // console.log('Change');
  }

  private extracted() {
    this.http.get<User>('http://localhost:8081/lkz_project_war_exploded/angular/user')
      .subscribe((user) => {
        this.user = user;
        console.log(this.user);
      });
  }


}
