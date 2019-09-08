import {User} from './User';
import {ActionType} from './ActionType';
import {Status} from './Status';

export class Action {
  id: number;
  actionType: ActionType;
  content: string;
  date: Date;
  userByIdImplementor: User;
  statusAfterProcessing: Status;
  notification: Notification;
}
