import {Component}          from 'angular2/core';
import {HTTP_PROVIDERS}     from 'angular2/http';
import {LoginParams}        from './login/login-form.model';
import {LoginFormComponent} from './login/login-form.component';
import {LoginService}       from './login/login-form.service';
import {EmailValidator}     from './helpers/email-validator';

// main component. doesn't do much. Injects necessary services.
@Component({
    selector: 'my-app',
    templateUrl: './app/app.component.html',
    directives: [LoginFormComponent],
    providers: [
        HTTP_PROVIDERS,
        LoginService,
        EmailValidator
    ]
})

export class AppComponent {
    public title = 'Simple AngularJS 2 Example';
}