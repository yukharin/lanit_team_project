import {User} from '../entity/User';
import {ActionType} from '../entity/ActionType';
import {Status} from '../entity/Status';
import {Notification} from '../entity/Notification';

export class AddAction4renderHtml {
  user: User;
  users: Array<User>;
  currentNotification: Notification;
  listActionType: Array<ActionType>;
  listStatus: Array<Status>;
}
