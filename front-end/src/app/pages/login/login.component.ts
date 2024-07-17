import { Component } from '@angular/core';
import { inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import {NONE_TYPE} from "@angular/compiler";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  authService = inject(AuthService);
  router = inject(Router);
  jsonObject: object = NONE_TYPE;

  protected loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  })

  onSubmit(){
    console.log(this.loginForm.valid)
    if(this.loginForm.valid){
      console.log(this.loginForm.value);
      console.log((this.authService.login(this.loginForm.value) + "ciao"));
      this.authService.login(this.loginForm.value)


        .subscribe((data: any) => {
          console.log(this.authService.isLoggedIn());
          if(this.authService.isLoggedIn()){
            this.jsonObject = JSON.parse(localStorage.getItem('authUser')!);
            //TODO
            //if(this.jsonObject.ruolo)

            this.router.navigate(['/admin']);
          }
          console.log(data);
        });

    }
  }

}
