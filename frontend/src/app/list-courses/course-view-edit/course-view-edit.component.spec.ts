import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseViewEditComponent } from './course-view-edit.component';

describe('CourseViewEditComponent', () => {
  let component: CourseViewEditComponent;
  let fixture: ComponentFixture<CourseViewEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseViewEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseViewEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
