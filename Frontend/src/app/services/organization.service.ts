import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Organization} from '../models/Organization';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  url = 'http://localhost:8080/lkz_project/account/organizations';


  constructor(private httpClient: HttpClient) {
  }

  getOrganizations(): Observable<Organization[]> {
    return this.httpClient.get<Organization[]>(this.url);
  }
}
