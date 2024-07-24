import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {Corso} from "../../interfaces/corso";
import {AuthService} from "../../auth/auth.service";
import {CorsiServiceService} from "../../services/corsiService/corsi-service.service";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Prenotazione} from "../../interfaces/prenotazione";

@Component({
  selector: 'app-home-instructor',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule
  ],
  templateUrl: './home-instructor.component.html',
  styleUrl: './home-instructor.component.css'
})
export class HomeInstructorComponent implements OnInit{
  corsiArray: Corso[] = [];
  authService = inject(AuthService);
  router = inject(Router);
  corsiService = inject(CorsiServiceService)


  protected corsoForm = new FormGroup({
    dataInizio: new FormControl('', [Validators.required]),
    dataFine: new FormControl('', [Validators.required]),
    nome: new FormControl('', [Validators.required]),
    maxPartecipanti: new FormControl('', [Validators.required]),
    imgSrc: new FormControl('', [Validators.required]),
    istruttore_id: new FormControl('', [Validators.required])
  })

  OnClick() {
    if (this.authService.isLoggedIn()) {
      this.authService.logout();
      this.router.navigate(['/login']);
    }
  }

  public getCorsi() {
    const retrievedUser = this.getUtenteInfo();
    this.corsiService.istruttoreCourses(retrievedUser.token)
      .subscribe({
        next: (data: Corso[]) => {
          this.corsiArray = data;
          console.log(this.corsiArray);
        }
      });
  }

  addCorso(){
    const retrievedUser = this.getUtenteInfo();
    this.corsoForm.controls['istruttore_id'].setValue(retrievedUser.id)
    console.log(retrievedUser.id + "ciao")

    this.corsiService.createCourse(this.corsoForm.value, retrievedUser.token).subscribe({
      next: (data: Corso ) => {
        this.corsiArray.push(data);
        console.log(data);
      }
    })
  }

  public deleteCorso(id: number){
    const retrievedUser = this.getUtenteInfo();
    this.corsiService.deleteCourse(retrievedUser.token, id).subscribe({
      next: () => {
        this.corsiArray.forEach( (corso, index) =>
        {if(corso.id == id){
          this.corsiArray.splice(index, 1);
        }})
      }
    })
  }

  private getUtenteInfo() {
    const jsonObject = localStorage.getItem('authUser');
    //TODO

    if (jsonObject) {
      const retrievedUser = JSON.parse(jsonObject);
      return retrievedUser;
    }
    return null;

  }

  ngOnInit(): void {
    this.getCorsi();
  }

}
