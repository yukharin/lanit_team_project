import {AbstractControl} from '@angular/forms';

export function ValidateResponseDate(control: AbstractControl) {
  const date: Date = control.value;
  if (Date.now() < date.getDate()) {
    return {validUrl: true};
  }
  return null;
}
