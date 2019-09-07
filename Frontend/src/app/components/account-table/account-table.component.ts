import {Component, OnInit} from '@angular/core';
import {AccountService} from '../../services/account.service';
import {NotificationService} from '../../services/notification.service';
import {PersonalAccountPageDto} from '../../models/PersonalAccountPageDto';
import {FormBuilder, FormGroup} from '@angular/forms';
import {TimeFilterService} from '../../services/time-filter.service';
import {SortParameterService} from '../../services/sort-parameter.service';
import {SortParameter} from '../../models/enums/SortParameter';
import {TimeFilter} from '../../models/enums/TimeFilter';

@Component({
  selector: 'app-account-table',
  templateUrl: './account-table.component.html',
  styleUrls: ['./account-table.component.css']
})
export class AccountTableComponent implements OnInit {

  personalAccount: PersonalAccountPageDto;
  filtersForm: FormGroup;
  timeFilters: TimeFilter[];
  sortParameters: SortParameter[];

  constructor(private notificationService: NotificationService, private accountService: AccountService
    ,         private formBuilder: FormBuilder, private timeFilterService: TimeFilterService,
              private sortParameterService: SortParameterService) {
  }

  ngOnInit(): void {
    this.getData();
  }

  public deleteNotification(id: string) {
    this.notificationService.deleteNotification(id).subscribe(() => this.getData());
  }

  public getData() {
    this.accountService.getPage().subscribe((res: PersonalAccountPageDto) => {
      this.personalAccount = res;
      this.personalAccount.page.number++;
      console.log(this.personalAccount);
      this.timeFilterService.getFilters().subscribe((filters: TimeFilter[]) => {
        this.timeFilters = filters;
      });
      this.sortParameterService.getSortParameters().subscribe((sortParameters: SortParameter[]) => {
        this.sortParameters = sortParameters;
      });
      this.filtersForm = this.formBuilder.group({
        newFilter: this.personalAccount.newFilter,
        inProcessingFilter: this.personalAccount.inProcessingFilter,
        rejectedFilter: this.personalAccount.rejectedFilter,
        approvedFilter: this.personalAccount.approvedFilter,
        timeFilter: this.personalAccount.timeFilter,
        sortParameter: this.personalAccount.sortParameter,
        reversedOrder: this.personalAccount.reversedOrder
      });
    });
  }

  pageChanged(event) {
    this.personalAccount.page.number = event - 1;
    this.personalAccount.page.content = [];
    this.accountService.getPageAndPass(this.personalAccount).subscribe((res: PersonalAccountPageDto) => {
      this.personalAccount = res;
      this.personalAccount.page.number++;
    });
  }

  applyFilters() {
    this.personalAccount.page.content = [];
    this.personalAccount.newFilter = this.filtersForm.controls.newFilter.value;
    this.personalAccount.inProcessingFilter = this.filtersForm.controls.inProcessingFilter.value;
    this.personalAccount.rejectedFilter = this.filtersForm.controls.rejectedFilter.value;
    this.personalAccount.approvedFilter = this.filtersForm.controls.approvedFilter.value;
    this.personalAccount.timeFilter = this.filtersForm.controls.timeFilter.value;
    this.personalAccount.sortParameter = this.filtersForm.controls.sortParameter.value;
    this.personalAccount.reversedOrder = this.filtersForm.controls.reversedOrder.value;
    this.personalAccount.page.number = 0;
    this.accountService.getPageAndPass(this.personalAccount).subscribe((response: PersonalAccountPageDto) => {
      this.personalAccount = response;
      this.personalAccount.page.number++;
    });
  }

}
