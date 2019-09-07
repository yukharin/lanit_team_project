import {Action} from './Action';
import {Organization} from './Organization';
import {User} from './User';
import {NotificationStatus} from './enums/NotificationStatus';

export interface Notification {

  id: string;
  notificationType: string;
  status: NotificationStatus;
  dateReceived: Date;
  dateResponse: Date;
  letterNumber: string;
  organization: Organization;
  userNotificationAuthor: User;
  actionsOfNotification: Action[];

}
