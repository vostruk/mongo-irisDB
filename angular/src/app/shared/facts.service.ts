import { Injectable } from '@angular/core';
import {Http, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Fact} from "./fact";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class FactsService {

  constructor(private http: Http) { }

  factsUrl = '/api/facts';

  getFacts() : Promise<Fact[]> {
    return this.http.get(this.factsUrl)
      .toPromise()
      .then((response) => response.json() as Fact[])
      .catch(this.handleError);
  }

  removeFact(id: string) {
    return this.http.delete(this.factsUrl + "/" + id)
      .toPromise()
      .catch(this.handleError);
  }

  addFact(fact: Fact) {
    let body = fact;
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({'headers': headers});

    return this.http.post(this.factsUrl, body, options)
      .toPromise()
      .catch(this.handleError);
  }

  updateFact(fact: Fact) {
    let body = fact;
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({'headers': headers});

    return this.http.put(this.factsUrl, body, options)
      .toPromise()
      .catch(this.handleError);
  }

  handleError(error: any) {
    console.log("Error occured", error);
    return Observable.throw(error);
  }
}
