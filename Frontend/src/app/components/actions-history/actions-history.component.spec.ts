import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionsHistoryComponent } from './actions-history.component';

describe('ActionsHistoryComponent', () => {
  let component: ActionsHistoryComponent;
  let fixture: ComponentFixture<ActionsHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActionsHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActionsHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
