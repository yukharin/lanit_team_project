/*
declarations: классы представлений (view classes), которые принадлежат модулю.
    Angular имеет три типа классов представлений: компоненты (components), директивы (directives), каналы (pipes)
exports: набор классов представлений, которые должны использоваться в шаблонах компонентов из других модулей
imports: другие модули, классы которых необходимы для шаблонов компонентов из текущего модуля
providers: классы, создающие сервисы, используемые модулем
bootstrap: корневой компонент, который вызывается по умолчанию при загрузке приложения
*/
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './component/app.component';
import { HttpClientModule } from '@angular/common/http';
import { ListUsersComponent} from './component/list-users/list-users.component';
import { NotificationsComponent} from './component/notifications/notifications.component';
import {RouterModule, Routes} from '@angular/router';
import {CommonHeaderUserComponent} from './component/common-header-user/common-header-user.component';
import {NgxPaginationModule} from 'ngx-pagination';
import {Actions} from './component/actions/actions';
import {AddAction} from './component/add-action/add-action';
import {NotFoundComponent} from './component/not-found.component/not-found.component';

const appRoutes: Routes = [
  // {path: 'account/notifications/:id', component: NotificationDetailComponent},
  // {path: 'account', component: AccountTableComponent},
  // {path: '', redirectTo: 'account', pathMatch: 'full'},
  // {path: 'account/notifications/:id/actions', component: ActionsHistoryComponent}

  {path: 'users', component: ListUsersComponent},
  {path: 'notifications', component: NotificationsComponent},
  {path: 'notifications/:id', component: Actions},
  //todo хакер может перебором по id получить доступ к уведомению
  //todo USE Guards (https://metanit.com/web/angular2/7.7.php)
  {path: 'notifications/:id/add_action', component: AddAction},
  {path: '', redirectTo: 'users', pathMatch: 'full'},
  { path: '**', component: NotFoundComponent }
];
/*
Если при разработке применяется webpack, нужно определить в файле webpack.config.js следующую секцию:
devServer: {
     historyApiFallback: true,
}
 */

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
  bootstrap:    [
    AppComponent
    // NotificationsComponent
    // CommonHeaderUserComponent
  ],
  providers: [],
})
export class AppModule { }
