import {Pipe, PipeTransform} from '@angular/core';
import {NotificationStatus} from '../models/enums/NotificationStatus';

@Pipe({
  name: 'notificationStatusPipe'
})
export class NotificationStatusPipe implements PipeTransform {

  transform(status: NotificationStatus): any {
    switch (status) {
      case NotificationStatus.NEW:
        return 'Новое';
      case NotificationStatus.IN_PROCESSING:
        return 'В обработке';
      case NotificationStatus.APPROVED:
        return 'Одобрено';
      case NotificationStatus.REJECTED:
        return 'Отклонено';
    }
  }

}
