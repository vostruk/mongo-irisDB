import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFactDialogComponent } from './add-fact-dialog.component';

describe('AddFactDialogComponent', () => {
  let component: AddFactDialogComponent;
  let fixture: ComponentFixture<AddFactDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFactDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFactDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
