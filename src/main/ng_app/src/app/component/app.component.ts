import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/entity/User';
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {Cabinet4renderHtml} from "../model/input-output/Cabinet4renderHtml";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  // providers: [HttpService]
})
export class AppComponent {
}
