import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Rule} from "./rule";
import 'rxjs/add/operator/toPromise';


@Injectable()
export class RulesService {

  constructor(private http: Http) { }

  rulesUrl = '/api/rules';

  getRules() : Promise<Rule[]> {
    return this.http.get(this.rulesUrl)
      .toPromise()
      .then((response) => response.json() as Rule[])
      .catch(this.handleError);
  }

  removeRule(id: string) {
    return this.http.delete(this.rulesUrl + "/" + id)
      .toPromise()
      .catch(this.handleError);
  }

  addRule(rule: Rule) {
    let body = rule;
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({'headers': headers});

    return this.http.post(this.rulesUrl, body, options)
      .toPromise()
      .catch(this.handleError);
  }

  updateRule(rule: Rule) {
    let body = rule;
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({'headers': headers});

    return this.http.put(this.rulesUrl, body, options)
      .toPromise()
      .catch(this.handleError);
  }

  handleError(error: any) {
    console.log("Error occured", error);
    return Observable.throw(error);
  }
}
