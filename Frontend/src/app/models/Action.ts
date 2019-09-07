import {ActionType} from './enums/ActionType';
import {User} from './User';
import {NotificationStatus} from './enums/NotificationStatus';
import {Notification} from './Notification';


export interface Action {
  id: string;
  actionType: ActionType;
  content: string;
  date: Date;
  implementor: User;
  statusAfterAction: NotificationStatus;
  notification: Notification;
}
