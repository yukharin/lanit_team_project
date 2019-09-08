import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SortParameter} from '../models/enums/SortParameter';

@Injectable({
  providedIn: 'root'
})
export class SortParameterService {

  url = 'http://localhost:8080/lkz_project/api/sortParameters';

  constructor(private httpClient: HttpClient) {
  }

  public getSortParameters(): Observable<SortParameter[]> {
    return this.httpClient.get<SortParameter[]>(this.url);
  }
}
