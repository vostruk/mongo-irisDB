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
  // factsMap: {name: string, facts: Fact[]}[] = [];
  factsMap: Map<string, Fact[]> = new Map<string, Fact[]>();
  factsNames: string[] = [];
  fact: Fact;

  constructor(private factsService: FactsService, public dialog: MdDialog) { }

  ngOnInit() {
    this.loadFacts();
    this.fact = null;
  }

  loadFacts() {
    this.factsService.getFacts().then(
      facts => {
        this.facts = facts;
        this.factsMap = new Map<string, Fact[]>();
        for (var fact of this.facts) {
          let name = fact.value.slice(0, fact.value.indexOf("("));
          if (this.factsMap.has(name)) {
            this.factsMap.get(name).push(fact);
          } else {
            let factsList: Fact[] = [];
            factsList.push(fact);
            this.factsMap.set(name, factsList)
          }
        }

        this.factsNames = Array.from(this.factsMap.keys());
      }
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

  getGroupHeading(factName: string) {
    return factName + " (" + this.factsMap.get(factName).length + ")";
  }
}

