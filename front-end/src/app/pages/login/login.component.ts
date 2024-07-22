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

  protected loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  })

  onSubmit(){
    console.log(this.loginForm.valid)
    if(this.loginForm.valid){
      console.log(this.loginForm.value);
      this.authService.login(this.loginForm.value)
        .subscribe((data: any) => {
          console.log(this.authService.isLoggedIn());
          if(this.authService.isLoggedIn()){
            const jsonObject = localStorage.getItem('authUser');
            //TODO

            if (jsonObject) {
              const retrievedUser = JSON.parse(jsonObject);
              console.log(retrievedUser.ruolo);
              if(retrievedUser.ruolo == "CLIENTE"){
                this.router.navigate(['/home-client']);
              }else if(retrievedUser.ruolo == "ISTRUTTORE"){
                this.router.navigate(['/home-instructor']);
              }
              else [
                console.log("The Role is not a valid one." +
                  " Home page for entered role vailable")
                ]
            }

            else{
              console.log("Saved Login Object not correctly saved to local storage")
            }
          }
        });

    }
  }

}
