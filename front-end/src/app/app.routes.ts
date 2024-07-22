import { Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import { SignupComponent } from './pages/signup/signup.component';
import { AdminComponent } from './pages/admin/admin.component';
import { authGuard } from './auth/auth.guard';
import {HomeClientComponent} from "./pages/home-client/home-client.component";
import {HomeInstructorComponent} from "./pages/home-instructor/home-instructor.component";
import {PrenotazioniComponent} from "./pages/prenotazioni/prenotazioni.component";

export const routes: Routes = [

  {
    path: '', redirectTo: '/login', pathMatch: 'full'
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'signup', component: SignupComponent
  },
  {
    path: 'home-client', component: HomeClientComponent
  },
  {
    path: 'home-instructor', component: HomeInstructorComponent
  },

  {
    path: 'admin', component: AdminComponent, canActivate: [authGuard]
  },
  {
    path:'prenotazioni', component: PrenotazioniComponent
  }


  ];
