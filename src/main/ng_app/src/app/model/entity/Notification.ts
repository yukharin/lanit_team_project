import {User} from './User';
import {Organization} from './Organization';
import {Status} from './Status';
// import {Action} from './Action';

export class Notification {
  id: number;
  notificationType: string;
  status: Status;
  dateReceived: Date;
  dateResponse: Date;
  letterNumber: string;
  organization: Organization;
  userByIdUserCuratorGos: User;
  userByIdUserImplementor: User;
  // actionsOfNotification: Action[];
}
