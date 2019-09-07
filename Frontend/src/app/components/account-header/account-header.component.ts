import {Component, OnInit} from '@angular/core';
import {AuthorizedUserService} from '../../services/authorized-user.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {User} from '../../models/User';

@Component({
  selector: 'app-account-header',
  templateUrl: './account-header.component.html',
  styleUrls: ['./account-header.component.css']
})
export class AccountHeaderComponent implements OnInit {

  user: User;
  observable: Observable<User>;

  constructor(private authorizedUserService: AuthorizedUserService, private router: Router) {
  }


  ngOnInit() {
    this.observable = this.authorizedUserService.getUser();
    this.observable.subscribe((user) => {
      this.user = user;
      console.log(user);
    });
  }
}


