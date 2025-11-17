import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuteurPage } from './page/auteur-page/auteur-page';

describe('AuteurPage', () => {
  let component: AuteurPage;
  let fixture: ComponentFixture<AuteurPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuteurPage]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AuteurPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
