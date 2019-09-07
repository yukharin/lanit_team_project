import {Pipe, PipeTransform} from '@angular/core';
import {TimeFilter} from '../models/enums/TimeFilter';

@Pipe({
  name: 'timeFilterPipe'
})
export class TimeFilterPipe implements PipeTransform {

  transform(timeFilter: TimeFilter): any {
    switch (timeFilter) {
      case TimeFilter.NO_FILTER:
        return 'Выберите срок предоставления ответа';
      case TimeFilter.THREE_DAYS:
        return '3 дня';
      case TimeFilter.TEN_DAYS:
        return '10 дней';
      case TimeFilter.THIRTY_DAYS:
        return '30 дней';
    }
  }

}
