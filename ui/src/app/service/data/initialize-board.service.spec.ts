import { TestBed } from '@angular/core/testing';

import { InitializeBoardService } from './initialize-board.service';

describe('InitializeBoardService', () => {
  let service: InitializeBoardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InitializeBoardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
