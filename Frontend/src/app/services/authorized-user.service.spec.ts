import { TestBed } from '@angular/core/testing';

import { AuthorizedUserService } from './authorized-user.service';

describe('AuthorizedUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthorizedUserService = TestBed.get(AuthorizedUserService);
    expect(service).toBeTruthy();
  });
});
