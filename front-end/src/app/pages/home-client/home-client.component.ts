import {Component, Input, input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { inject } from '@angular/core';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import {CorsiServiceService} from "../../services/corsiService/corsi-service.service";
import {Corso} from "../../interfaces/corso";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-home-client',
  standalone: true,
  imports: [RouterLink, RouterOutlet, NgOptimizedImage],
  templateUrl: './home-client.component.html',
  styleUrl: './home-client.component.css'
})
export class HomeClientComponent implements OnInit{

  corsiArray: Corso[] = [];
  authService  =  inject(AuthService);
  router  =  inject(Router);
  corsiService = inject(CorsiServiceService)

  OnClick() {
    if(this.authService.isLoggedIn()){
      this.authService.logout();
      this.router.navigate(['/login']);
    }
  }

  public getCorsi(){
    this.corsiService.availableCourses()
      .subscribe({
        next: (data:Corso[]) => {
          this.corsiArray = data;
          console.log(this.corsiArray);
          console.log(this.corsiArray[1])
        }
      });
  }

  ngOnInit(){
    this.getCorsi();

  }


}
