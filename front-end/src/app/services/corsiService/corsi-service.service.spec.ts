import { TestBed } from '@angular/core/testing';

import { CorsiServiceService } from './corsi-service.service';

describe('CorsiServiceService', () => {
  let service: CorsiServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CorsiServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
