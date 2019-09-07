import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {NotificationService} from '../../services/notification.service';
import {Action} from '../../models/Action';
import {Notification} from '../../models/Notification';


@Component({
  selector: 'app-actions-history',
  templateUrl: './actions-history.component.html',
  styleUrls: ['./actions-history.component.css']
})
export class ActionsHistoryComponent implements OnInit {

  notification: Notification;
  actions: Action[];

  constructor(private route: ActivatedRoute, private notificationService: NotificationService) {

  }


  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.notificationService.getNotification(id).subscribe((res: Notification) => {
      this.notification = res;
      this.actions = res.actionsOfNotification;
    });
  }

}
