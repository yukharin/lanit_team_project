import {JsonPageImpl} from './JsonPageImpl';
import {SortParameter} from './enums/SortParameter';
import {TimeFilter} from './enums/TimeFilter';


export interface PersonalAccountPageDto {

  timeFilter: TimeFilter;
  newFilter: boolean;
  inProcessingFilter: boolean;
  approvedFilter: boolean;
  rejectedFilter: boolean;
  page: JsonPageImpl;
  sortParameter: SortParameter;
  reversedOrder: boolean;

}
