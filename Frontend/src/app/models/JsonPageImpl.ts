import {PageRequest} from './PageRequest';
import {Notification} from './Notification';

export interface JsonPageImpl {

  content: Notification[];
  pageable: PageRequest;
  last: boolean;
  totalPages: number;
  totalElements: number;
  first: boolean;
  numberOfElements: number;
  size: number;
  number: number;

}
