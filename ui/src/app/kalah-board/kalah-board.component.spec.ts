import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KalahBoardComponent } from './kalah-board.component';

describe('KalahBoardComponent', () => {
  let component: KalahBoardComponent;
  let fixture: ComponentFixture<KalahBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KalahBoardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(KalahBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
