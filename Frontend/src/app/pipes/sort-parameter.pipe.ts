import {Pipe, PipeTransform} from '@angular/core';
import {SortParameter} from '../models/enums/SortParameter';

@Pipe({
  name: 'sortParameterPipe'
})
export class SortParameterPipe implements PipeTransform {

  transform(sortParameter: SortParameter): any {
    switch (sortParameter) {
      case SortParameter.BY_DATE_RECEIVED:
        return 'По дате получения';
      case SortParameter.BY_DATE_RESPONSE:
        return 'По дате предоставления ответа';
      case SortParameter.BY_ORGANIZATION:
        return 'По организации';
      case SortParameter.BY_STATUS:
        return 'По статусу';
    }
  }

}
