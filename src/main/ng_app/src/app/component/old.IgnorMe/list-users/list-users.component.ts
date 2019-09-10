import {Component, EventEmitter, OnInit, Output} from '@angular/core';
// import { ActionsService} from './http.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../../../model/entity/User';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  // selector: 'app-root',
  selector: 'app-list-users-root',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css'],
  // providers: [ActionsService]
})
export class ListUsersComponent implements OnInit {

  users: User[];

  constructor(
    // private httpService: ActionsService,
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.http.get<User[]>('http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/input/users')
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

    this.http.post<boolean>('http://localhost:8080/lkz_project-1.0-SNAPSHOT/angular/input/has_user', body, httpOptions)
      .subscribe((hasUser) => {

        if (hasUser) this.router.navigate(['/notifications']);
      });
  }
}
