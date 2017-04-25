import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Query} from "./query";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class QueriesService {

  queriesUrl = '/api/query';

  constructor(private http: Http) { }

  resolveQuery(query: Query) : Promise<Query> {
    let body = query;
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({'headers': headers});

    return this.http.post(this.queriesUrl, body, options)
      .toPromise()
      .then(response => response.json() as Query)
      .catch(this.handleError);
  }

  handleError(error: any) {
    console.log("Error occured", error);
    return Observable.throw(error);
  }
}
