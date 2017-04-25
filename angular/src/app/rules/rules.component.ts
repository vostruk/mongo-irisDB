import { Component, OnInit } from '@angular/core';
import {RulesService} from "../shared/rules.service";
import {MdDialog} from "@angular/material";
import {Rule} from "../shared/rule";
import {AddRuleDialogComponent} from "./add-rule-dialog/add-rule-dialog.component";


@Component({
  selector: 'app-rules',
  templateUrl: './rules.component.html',
  styleUrls: ['./rules.component.css']
})
export class RulesComponent implements OnInit {

  rules: Rule[] = [];
  rule: Rule;

  constructor(private rulesService: RulesService, public dialog: MdDialog) { }

  ngOnInit() {
    this.loadRules();
    this.rule = null;
    setInterval(() => {
      this.loadRules();
    }, 5000);
  }

  loadRules() {
    this.rulesService.getRules().then(
      rules => this.rules = rules
    );
  }

  removeRule(rule: Rule) {
    if (confirm("Czy jesteś pewien że chcesz usunąć wybraną regułę?")) {
      this.rulesService.removeRule(rule.id)
        .then(() => {
          this.loadRules()
        });
    }
  }

  openAddRuleDialog() {
    let dialogRef = this.dialog.open(AddRuleDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        this.rule = new Rule(null, result);
        this.rulesService.addRule(this.rule)
          .then(() => {
            this.loadRules()
          });
      }
    });
  }
}
