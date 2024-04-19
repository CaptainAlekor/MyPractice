import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorGroupCoursesPageComponent } from './professor-group-courses-page.component';

describe('ProfessorGroupCoursesPageComponent', () => {
  let component: ProfessorGroupCoursesPageComponent;
  let fixture: ComponentFixture<ProfessorGroupCoursesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfessorGroupCoursesPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProfessorGroupCoursesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
