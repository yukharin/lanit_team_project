import {Pageable} from './Pageable';
import {Sort} from './Sort';
import {Action} from '../../entity/Action';

export interface PageActionImpl {

  content: Action[];
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
