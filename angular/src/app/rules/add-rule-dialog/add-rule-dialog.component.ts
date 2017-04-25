import { Component, OnInit } from '@angular/core';
import { MdDialogRef } from '@angular/material';

@Component({
  selector: 'app-add-rule-dialog',
  templateUrl: './add-rule-dialog.component.html',
  styleUrls: ['./add-rule-dialog.component.css']
})
export class AddRuleDialogComponent implements OnInit {

  value: string = '';

  constructor(public dialogRef: MdDialogRef<AddRuleDialogComponent>) { }

  ngOnInit() {
  }

  canAddRule() {
    return this.value !== '';
  }
}
