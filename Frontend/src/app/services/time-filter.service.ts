import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TimeFilter} from '../models/enums/TimeFilter';

@Injectable({
  providedIn: 'root'
})
export class TimeFilterService {

  url = 'http://localhost:8080/lkz_project/api/timeFilters';

  constructor(private httpClient: HttpClient) {
  }

  public getFilters(): Observable<TimeFilter[]> {
    return this.httpClient.get<TimeFilter[]>(this.url);
  }

}
