import { Component, OnInit } from '@angular/core';
import {FactsService} from "../shared/facts.service";
import {Fact} from "../shared/fact";
import {AddFactDialogComponent} from "./add-fact-dialog/add-fact-dialog.component";
import {MdDialog} from "@angular/material";

@Component({
  selector: 'app-facts',
  templateUrl: './facts.component.html',
  styleUrls: ['./facts.component.css']
})
export class FactsComponent implements OnInit {

  facts: Fact[] = [];
  fact: Fact;

  constructor(private factsService: FactsService, public dialog: MdDialog) { }

  ngOnInit() {
    this.loadFacts();
    this.fact = null;
    setInterval(() => {
      this.loadFacts();
    }, 5000);
  }

  loadFacts() {
    this.factsService.getFacts().then(
      facts => this.facts = facts
    );
  }

  removeFact(fact: Fact) {
    if (confirm("Czy jesteś pewien że chcesz usunąć wybrany fakt?")) {
      this.factsService.removeFact(fact.id)
        .then(() => {
          this.loadFacts()
        });
    }
  }

  openAddFactDialog() {
    let dialogRef = this.dialog.open(AddFactDialogComponent);
    dialogRef.afterClosed().subscribe(result => {
      if (result != null) {
        this.fact = new Fact(null, result);
        this.factsService.addFact(this.fact)
          .then(() => {
            this.loadFacts()
          });
      }
    });
  }
}
