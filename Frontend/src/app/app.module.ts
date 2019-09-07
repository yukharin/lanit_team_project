import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {AccountHeaderComponent} from './components/account-header/account-header.component';
import {HttpClientModule} from '@angular/common/http';
import {AccountTableComponent} from './components/account-table/account-table.component';
import {RouterModule} from '@angular/router';
import {NotificationDetailComponent} from './components/notification-detail/notification-detail.component';
import {ActionsHistoryComponent} from './components/actions-history/actions-history.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {AddNotificationComponent} from './components/add-notification/add-notification.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ActionTypePipe } from './pipes/action-type.pipe';
import {NotificationStatusPipe} from './pipes/notification-status.pipe';
import { SortParameterPipe } from './pipes/sort-parameter.pipe';
import { TimeFilterPipe } from './pipes/time-filter.pipe';
import { DateFormatPipe } from './pipes/date-format.pipe';

@NgModule({
  declarations: [
    AppComponent,
    AccountHeaderComponent,
    AccountTableComponent,
    NotificationDetailComponent,
    ActionsHistoryComponent,
    AddNotificationComponent,
    NotificationStatusPipe,
    ActionTypePipe,
    SortParameterPipe,
    TimeFilterPipe,
    DateFormatPipe
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: 'notifications/:id', component: NotificationDetailComponent},
      {path: '', component: AccountTableComponent},
      {path: 'notifications/:id/actions', component: ActionsHistoryComponent},
      {path: 'add', component: AddNotificationComponent}
    ])
  ],
  providers: [NotificationStatusPipe, ActionTypePipe, SortParameterPipe, TimeFilterPipe, DateFormatPipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
