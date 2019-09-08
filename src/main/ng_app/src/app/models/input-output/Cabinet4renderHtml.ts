import {PageImpl} from './pagination/PageImpl';
import {CabinetSessionState} from './CabinetSessionState';
import {Status} from '../entity/Status';
import {PageNotificationImpl} from './pagination/PageNotificationImpl';
import {User} from '../entity/User';
import {ColumnCabinetTable} from './ColumnCabinetTable';

export class Cabinet4renderHtml {
  statuses4selectFilter: Array<Status>;
  listArchiveStatus: Array<Status>;
  checkedMainListNotificStatuses: Array<Status>;

  // pageImpl: PageImpl<Notification>;
  pageImpl: PageNotificationImpl;
  state: CabinetSessionState;

  newCheckedMainListNotificStatusesId: Array<number>;

  user: User;
  selectShowListMaxResult: Array<number>;
  columnTable: Array<ColumnCabinetTable>;
  
}
