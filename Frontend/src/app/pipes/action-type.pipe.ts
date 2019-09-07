import {Pipe, PipeTransform} from '@angular/core';
import {ActionType} from '../models/enums/ActionType';

@Pipe({
  name: 'actionTypePipe'
})
export class ActionTypePipe implements PipeTransform {

  transform(actionType: ActionType): any {
    switch (actionType) {
      case ActionType.APPROVE:
        return 'Одобрить';
      case ActionType.SEND_TO_PROCESSING:
        return 'Отправить в обработку';
      case ActionType.REJECT:
        return 'Отклонить';
      case ActionType.CREATE:
        return 'Создать';
    }
  }

}
