import {Component, OnInit} from '@angular/core';
import {OrganizationService} from '../../services/organization.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Organization} from '../../models/Organization';
import {AccountService} from '../../services/account.service';
import {Notification} from '../../models/Notification';
import {DateFormatPipe} from '../../pipes/date-format.pipe';
import {Router} from '@angular/router';
import {ValidateResponseDate} from '../../validators/ValidateResponseDate';


@Component({
  selector: 'app-add-notification',
  templateUrl: './add-notification.component.html',
  styleUrls: ['./add-notification.component.css']
})
export class AddNotificationComponent implements OnInit {

  organizations: Organization[];
  notificationForm: FormGroup;
  notification: Notification;

  constructor(private organizationService: OrganizationService, private formBuilder: FormBuilder, private accountService: AccountService,
              private dateFormatPipe: DateFormatPipe, private router: Router) {
    this.notification = {
      id: undefined,
      notificationType: undefined,
      status: undefined,
      dateReceived: undefined,
      dateResponse: undefined,
      letterNumber: undefined,
      organization: {
        id: undefined,
        name: undefined
      },
      userNotificationAuthor: undefined,
      actionsOfNotification: undefined
    };
  }

  ngOnInit() {
    this.initForm();
    this.initOrganizations();
  }

  private initForm() {
    this.notificationForm = this.formBuilder.group({
      notificationType: new FormControl(null, [Validators.required, Validators.minLength(7)]),
      dateResponse: new FormControl(null, Validators.required),
      organization: new FormControl(null, Validators.required)
    });
  }

  private initOrganizations() {
    this.organizationService.getOrganizations().subscribe((res: Organization[]) => {
      this.organizations = res;
    });
  }

  submitForm() {
    this.notification.notificationType = this.notificationForm.controls.notificationType.value;
    console.log(this.dateFormatPipe.transform(this.notificationForm.controls.dateResponse.value));
    this.notification.dateResponse = this.dateFormatPipe.transform(this.notificationForm.controls.dateResponse.value);
    this.notification.organization.id = this.notificationForm.controls.organization.value;
    this.accountService.addNotification(this.notification).subscribe(() => {
      this.router.navigateByUrl('');
    });
  };
}
