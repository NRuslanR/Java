import { TestBed } from '@angular/core/testing';

import { InMemoryHeroesDataService } from './in-memory-heroes-data-service.service';

describe('InMemoryHeroesDataService', () => {
  let service: InMemoryHeroesDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InMemoryHeroesDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
