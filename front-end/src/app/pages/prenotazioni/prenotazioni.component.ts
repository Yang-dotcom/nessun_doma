import {Component, EventEmitter, inject, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgFor} from "@angular/common";
import {RouterLink} from "@angular/router";
import {PrenotazioniServiceService} from "../../services/prenotazioniService/prenotazioni-service.service";
import {Prenotazione} from "../../interfaces/prenotazione";
import {Corso} from "../../interfaces/corso";
import {CorsiServiceService} from "../../services/corsiService/corsi-service.service";


@Component({
  selector: 'app-prenotazioni',
  templateUrl: './prenotazioni.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgFor,
    ReactiveFormsModule,
    RouterLink
  ],
  styleUrls: ['./prenotazioni.component.css']
})
export class PrenotazioniComponent implements OnInit{
  userName : string= '';
  corsiArray: Corso[] = [];
  prenotazioniArray: Prenotazione[] = [];
  corsiService = inject(CorsiServiceService)
  prenotazioniService = inject(PrenotazioniServiceService);

  protected prenotazioneForm = new FormGroup({
    dataPrenotazione: new FormControl('', [Validators.required]),
    corsoName: new FormControl('', [Validators.required]),
    utenteEmail: new FormControl('', [Validators.required])
  })

  addPrenotazione(){
    const retrievedUser = this.getUtenteInfo();
    this.prenotazioneForm.controls['utenteEmail'].setValue(retrievedUser.email)
    console.log(retrievedUser.email + "ciao")

    this.prenotazioniService.createPrenotazione(this.prenotazioneForm.value, retrievedUser.token).subscribe({
      next: (data: Prenotazione) => {
        this.prenotazioniArray.push(data);
        console.log(data);
      }
    })
  }

  public deletePrenotazione(id: number){
    const retrievedUser = this.getUtenteInfo();
    this.prenotazioniService.deletePrenotazione(retrievedUser.token, id).subscribe({
      next: () => {
        this.prenotazioniArray.forEach( (prenotazione, index) =>
        {if(prenotazione.id == id){
          this.prenotazioniArray.splice(index, 1);
        }})
      }
    })
  }

  public getPrenotazioni() {
    const retrievedUser = this.getUtenteInfo();
    this.prenotazioniService.availablePrenotazioni(retrievedUser.token).subscribe({
      next: (data: Prenotazione[]) => {
        this.prenotazioniArray = data;
        console.log(this.prenotazioniArray);
      }
    })
  }

  public getCorsi(){
    this.corsiService.availableCourses()
      .subscribe({
        next: (data:Corso[]) => {
          this.corsiArray = data;
          console.log(this.corsiArray);
        }
      });
  }

  ngOnInit(){
    this.getPrenotazioni();
    this.getCorsi();
    this.userName =this.getUtenteInfo().email;
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




}
