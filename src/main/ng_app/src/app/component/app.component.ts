import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
// import { HttpService} from './http.service';
import {HttpClient} from '@angular/common/http';
import {TestUser} from '../models/test/TestUser';
import {Observable} from 'rxjs';
import {User} from '../models/entity/User';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  // providers: [HttpService]
})
export class AppComponent  {}
  // implements OnInit, OnChanges



  // userFromParent: User;

  // constructor(private http: HttpClient) { }
  // @Output() userNameChange = new EventEmitter<User>();

  // onNameChange(model: User) {
  //   this.user = model;
  //   this.userNameChange.emit(model);
  // }

  // ngOnInit() {
  //   console.log('init');
    // this.updateUser();
  // }

  // private updateUser() {
  //   this.http.get<User>('http://localhost:8081/lkz_project_war_exploded/angular/user')
  //     .subscribe((user) => {
  //       this.userFromParent = user;
  //       console.log(this.userFromParent);
  //     });
  // }

  // onChangeUserFromParent(id: number): void {
  //   this.http.get<User>('http://localhost:8081/lkz_project_war_exploded/angular/user?id=' + id)
  //     .subscribe((user) => {
  //       this.userFromParent = user;
  //       console.log(this.userFromParent);
  //     });
  //   console.log('onChangeUserFromParent');
  // }

  // ngOnChanges() {
  //   console.log('Change');
  // }
// }
