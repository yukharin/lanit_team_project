import {PageImpl} from './pagination/PageImpl';
import {TheNotificationSessionState} from './TheNotificationSessionState';
import {Status} from '../entity/Status';
import {Action} from '../entity/Action';
import {PageActionImpl} from './pagination/PageActionImpl';
import {User} from '../entity/User';
import {ColumnCabinetTable} from './ColumnCabinetTable';
import {ActionType} from '../entity/ActionType';
import {Notification} from '../entity/Notification';
import {ColumnTheNotificationTable} from './ColumnTheNotificationTable';

export class TheNotification4renderHtml {
  // statuses4selectFilter: Array<Status>;
  // statuses4selectFilter: Array<Status>;
  // listArchiveStatus: Array<Status>;
  // checkedMainListNotificStatuses: Array<Status>;
  // newCheckedMainListNotificStatusesId: Array<number>;

  // showListActions: Array<Status>
  latestAction: Action;

  // pageImpl: PageImpl<Action>;
  pageImpl: PageActionImpl;
  state: TheNotificationSessionState;

  user: User;
  currentNotification: Notification;
  selectShowListMaxResult: Array<number>;
  columnTable: Array<ColumnCabinetTable>;
  listActionType: Array<ActionType>;
  listStatus: Array<Status>;
}
