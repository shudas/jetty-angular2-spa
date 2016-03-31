import {Injectable}     from 'angular2/core';
import {Http, Response, Headers, RequestOptions} from 'angular2/http';
import {LoginParams}    from './login-form.model';
import {Observable}     from 'rxjs/Observable';

// Service related to login page
@Injectable()
export class LoginService {
    constructor (private http: Http) {}

    private _pingUrl = 'api/health/ping';
    private _loginUrl = 'api/auth/login';  // URL to web api

    // if you want to ping the server
    getPing() {
        return this.http.get(this._pingUrl)
                .map(res => <string> res.text())
                .do(data => console.log(data))
                .catch(this.handleError);
    }

    // submit login params
    submitLogin(params: LoginParams) {
        let body = JSON.stringify(params);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this._loginUrl, body, options)
                .map(res => <string> res.text())
                .do(data => console.log(data))
                .catch(this.handleError);
    }

    private handleError (error: Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}