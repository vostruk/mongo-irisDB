import { Component, OnInit } from '@angular/core';
import {QueriesService} from "../shared/queries.service";
import {Query} from "../shared/query";

@Component({
  selector: 'app-queries',
  templateUrl: './queries.component.html',
  styleUrls: ['./queries.component.css']
})
export class QueriesComponent implements OnInit {

  query: Query = new Query('', '');
  constructor(private queriesService: QueriesService) { }

  ngOnInit() {
  }

  canResolveQuery() {
    return this.query.value !== '';
  }

  displayResult() {
    return this.query.result !== '';
  }

  resolveQuery() {
    this.queriesService.resolveQuery(this.query)
      .then(query => this.query = query);
  }

}
