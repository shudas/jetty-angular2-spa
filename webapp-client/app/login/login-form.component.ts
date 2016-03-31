import {Component} from 'angular2/core';
import {NgClass} from 'angular2/common';
import {LoginParams} from './login-form.model';
import {LoginService} from './login-form.service';
import {EmailValidator} from '../helpers/email-validator';

@Component({
    selector: 'login-form',
    templateUrl: './app/login/login-form.component.html',
    styles:[`.active {background-color: red;}`],
    directives: [NgClass]
})

export class LoginFormComponent {
    // inject necessary services
    constructor (private _loginService: LoginService, private _emailValidator: EmailValidator) {}

    params: LoginParams = new LoginParams();
    serverResponse: string = '';
    // this is used in our template for CSS binding
    isValidEmail: boolean = false;

    // function called by template to submit the request
    onLoginSubmit() {
        // reset error message every time so we don't show old error messages
        this._loginService.submitLogin(this.params)
            .subscribe(
                res => this.serverResponse = res,
                error => this.serverResponse = <any>error);
    }

    // simple email validation. should be better validated in BE.
    onEmailKey() {
        this.isValidEmail = this._emailValidator.isValidEmail(this.params.email);
    }

    // just for debugging. Don't actually keep this in your final project. that would look stupid.
    get diagnostic() { return JSON.stringify(this.params) }
}