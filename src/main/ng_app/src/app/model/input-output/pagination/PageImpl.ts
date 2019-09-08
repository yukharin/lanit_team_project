import {Pageable} from './Pageable';
import {Sort} from './Sort';
import {Notification} from '../../entity/Notification';

export interface PageImpl<T> {

  content: T[];
  pageable: Pageable;
  last: boolean;
  totalPages: number;
  totalElements: number;
  first: boolean;
  sort: Sort;
  numberOfElements: number;
  size: number;
  number: number;

}
