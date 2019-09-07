import { TestBed } from '@angular/core/testing';

import { SortParameterService } from './sort-parameter.service';

describe('SortParameterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SortParameterService = TestBed.get(SortParameterService);
    expect(service).toBeTruthy();
  });
});
