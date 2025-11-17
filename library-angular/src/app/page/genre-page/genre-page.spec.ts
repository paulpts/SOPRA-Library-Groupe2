import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenrePage } from './genre-page';

describe('GenrePage', () => {
  let component: GenrePage;
  let fixture: ComponentFixture<GenrePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GenrePage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GenrePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
