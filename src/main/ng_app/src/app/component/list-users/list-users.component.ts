import {Component, EventEmitter, OnInit, Output} from '@angular/core';
// import { HttpService} from './http.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TestUser} from '../../models/test/TestUser';
import {User} from '../../models/entity/User';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  // selector: 'app-root',
  selector: 'app-list-users-root',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css'],
  // providers: [HttpService]
})
export class ListUsersComponent implements OnInit {

  // testUser: TestUser;
  // testUsers: TestUser[];
  users: User[];

  // @Output()
  // onChangedUser = new EventEmitter<number>();
  //
  // selectUser(userId: number) {
  //   this.onChangedUser.emit(userId);
  //   console.log('selectUser');
  //   routerLink="['/notifications', user?.id]
  //   this.route.
  // }

  // response: any;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }
  // constructor(private httpService: HttpService) {}

  ngOnInit() {
    // this.http.get('user.json') //Observable<any>
    // this.httpService.getUsers()
    // .subscribe((data:User) => this.user=data);
    // .subscribe(data => this.list-users=data["userList"]);
    //   .subscribe(data => this.list-users = data);

    // this.http.get<TestUser>('http://localhost:8081/lkz_project_war_exploded/angular/test_user')
    // .subscribe((testUser) => {
    //   this.testUser = testUser;
    //   console.log(this.testUser);
    // });
    //
    // this.http.get<TestUser[]>('http://localhost:8081/lkz_project_war_exploded/angular/test_users')
    // .subscribe((testUsers) => {
    //   this.testUsers = testUsers;
    //   console.log(this.testUsers);
    // });

    this.http.get<User[]>('http://localhost:8081/lkz_project_war_exploded/angular/input/users')
    .subscribe((users) => {
      this.users = users;
      console.log(this.users);
    });
  }

  input(user: User) {

    const httpOptions = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };
    let body = new HttpParams();
    body = body.set('id', user.id.toString());

    this.http.post<boolean>('http://localhost:8081/lkz_project_war_exploded/angular/input/has_user', body, httpOptions)
      .subscribe((hasUser) => {

        if (hasUser) this.router.navigate(['/notifications']);
      });
  }
}
