import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { MaterialModule } from '@angular/material';
import { BrowserAnimationsModule  } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { RulesComponent } from './rules/rules.component';
import { FactsComponent } from './facts/facts.component';
import { QueriesComponent } from './queries/queries.component';

import {FactsService} from "./shared/facts.service";
import { AddFactDialogComponent } from './facts/add-fact-dialog/add-fact-dialog.component';
import { AddRuleDialogComponent } from './rules/add-rule-dialog/add-rule-dialog.component';
import {RulesService} from "./shared/rules.service";
import {QueriesService} from "./shared/queries.service";

@NgModule({
  declarations: [
    AppComponent,
    RulesComponent,
    FactsComponent,
    QueriesComponent,
    AddFactDialogComponent,
    AddRuleDialogComponent
  ],
  entryComponents: [AddFactDialogComponent, AddRuleDialogComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterialModule,
    BrowserAnimationsModule
  ],
  providers: [FactsService, RulesService, QueriesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
