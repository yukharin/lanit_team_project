import { Component} from '@angular/core';

@Component({
  // selector: 'not-found-app',
  selector: 'app-root',
  template: `<h3>Страница не найдена</h3>
  <h2><button [routerLink]="['']">перейти на главную</button></h2>`
})
export class NotFoundComponent { }
