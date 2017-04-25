import { Component, OnInit } from '@angular/core';
import { MdDialogRef } from '@angular/material';

@Component({
  selector: 'app-add-fact-dialog',
  templateUrl: './add-fact-dialog.component.html',
  styleUrls: ['./add-fact-dialog.component.css']
})
export class AddFactDialogComponent implements OnInit {

  value: string = '';

  constructor(public dialogRef: MdDialogRef<AddFactDialogComponent>) { }

  ngOnInit() {
  }

  canAddFact() {
    return this.value !== '';
  }
}
