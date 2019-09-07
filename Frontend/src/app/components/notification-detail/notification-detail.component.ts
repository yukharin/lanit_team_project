import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NotificationService} from '../../services/notification.service';
import {ActionType} from '../../models/enums/ActionType';
import {Notification} from '../../models/Notification';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Action} from '../../models/Action';
import {AccountService} from '../../services/account.service';
import {NotificationStatus} from '../../models/enums/NotificationStatus';


@Component({
  selector: 'app-notification-detail',
  templateUrl: './notification-detail.component.html',
  styleUrls: ['./notification-detail.component.css']
})
export class NotificationDetailComponent implements OnInit {

  notification: Notification;
  actionTypes: Set<ActionType>;
  actionForm: FormGroup;

  constructor(private route: ActivatedRoute, private notificationService: NotificationService, private formBuilder: FormBuilder, private accountService: AccountService
    , private router: Router) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.notificationService.getNotification(id).subscribe(((notification: Notification) => {
      this.notification = notification;
      this.actionForm = this.formBuilder.group({
        actionType: new FormControl(null, Validators.required),
        content: new FormControl(null, [Validators.required, Validators.minLength(5)])
      });
      this.actionTypes = this.defineActions(this.notification.status);
    }));
  }

  public defineActions(status: NotificationStatus): Set<ActionType> {
    const actionTypes = new Set<ActionType>();
    switch (status) {
      case NotificationStatus.NEW:
        actionTypes.add(ActionType.SEND_TO_PROCESSING);
        break;
      case NotificationStatus.IN_PROCESSING:
        actionTypes.add(ActionType.APPROVE);
        actionTypes.add(ActionType.REJECT);
        break;
    }
    return actionTypes;
  }

  public addAction() {
    const action: Action = {
      actionType: undefined,
      date: undefined,
      id: undefined,
      implementor: undefined,
      notification: undefined,
      content: undefined,
      statusAfterAction: undefined
    };
    action.actionType = this.actionForm.controls.actionType.value;
    action.content = this.actionForm.controls.content.value;
    action.notification = {
      id: this.notification.id,
      notificationType: null,
      status: null,
      dateResponse: null,
      dateReceived: null,
      letterNumber: null,
      organization: null,
      userNotificationAuthor: null,
      actionsOfNotification: null
    };
    this.accountService.addAction(action).subscribe(() => {
      this.router.navigateByUrl('');
    });
  }
}
