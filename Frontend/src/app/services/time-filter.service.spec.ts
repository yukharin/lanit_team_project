import { TestBed } from '@angular/core/testing';

import { TimeFilterService } from './time-filter.service';

describe('TimeFilterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TimeFilterService = TestBed.get(TimeFilterService);
    expect(service).toBeTruthy();
  });
});
