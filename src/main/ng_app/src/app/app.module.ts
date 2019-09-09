import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './component/app.component';
import { HttpClientModule } from '@angular/common/http';
import { ListUsersComponent} from './component/old.IgnorMe/list-users/list-users.component';
import { NotificationsComponent} from './component/notifications/notifications.component';
import {RouterModule, Routes} from '@angular/router';
import {CommonHeaderUserComponent} from './component/common-header-user/common-header-user.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {Actions} from './component/actions/actions';
import {AddAction} from './component/add-action/add-action';
import {NotFoundComponent} from './component/not-found.component/not-found.component';

//todo move in app-routing.module.ts
const appRoutes: Routes = [
  // {path: 'users', component: ListUsersComponent},
  {path: 'notifications', component: NotificationsComponent},
  {path: 'notifications/:id', component: Actions},
  //todo хакер может перебором по id получить доступ к уведомению
  //todo USE Guards (https://metanit.com/web/angular2/7.7.php)
  {path: 'notifications/:id/add_action', component: AddAction},
  // {path: '', redirectTo: 'users', pathMatch: 'full'},
  {path: '', redirectTo: 'notifications', pathMatch: 'full'},
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule, // HttpModule = depricated
    ReactiveFormsModule,
    NgxPaginationModule,
    RouterModule.forRoot(appRoutes)
  ],
  declarations: [
    NotFoundComponent,
    AppComponent,
    CommonHeaderUserComponent,
    ListUsersComponent,
    NotificationsComponent,
    Actions,
    AddAction
  ],
  bootstrap: [AppComponent],
  providers: [],
})
export class AppModule { }
//todo add service (create var url in one path)
//todo binding между components
