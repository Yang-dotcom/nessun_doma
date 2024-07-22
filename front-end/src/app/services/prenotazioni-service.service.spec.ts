import { TestBed } from '@angular/core/testing';

import { PrenotazioniServiceService } from './prenotazioniService/prenotazioni-service.service';

describe('PrenotazioniServiceService', () => {
  let service: PrenotazioniServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrenotazioniServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
