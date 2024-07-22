import {Component, inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgFor} from "@angular/common";
import {RouterLink} from "@angular/router";
import {PrenotazioniServiceService} from "../../services/prenotazioniService/prenotazioni-service.service";
import {Prenotazione} from "../../interfaces/prenotazione";

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
  newBooking: string = '';
  bookings: string[] = [];
  prenotazioniArray: Prenotazione[] = [];
  prenotazioniService = inject(PrenotazioniServiceService);

  protected prenotazioneForm = new FormGroup({
    dataPrenotazione: new FormControl('', [Validators.required]),
    corsoName: new FormControl('', [Validators.required]),
    utenteEmail: new FormControl(this.getUtenteInfo().utenteEmail, [Validators.required])
  })
  addBooking() {
    if (this.newBooking.trim()) {
      this.bookings.push(this.newBooking.trim());
      this.newBooking = '';
    }
  }

  OnSubmit() {
    this.addPrenotazione(this.prenotazioneForm);
  }

  addPrenotazione(body: any){
    const jsonObject = localStorage.getItem('authUser');
    //TODO
    if (jsonObject) {
      const retrievedUser = JSON.parse(jsonObject);
      console.log(retrievedUser.ruolo);

      this.prenotazioniService.createPrenotazione(body, retrievedUser.token).subscribe({
        next: (data: Prenotazione) => {
          console.log(data);
        }
      })
    }

  }

  public getPrenotazioni() {
    const jsonObject = localStorage.getItem('authUser');
    //TODO

    if (jsonObject) {
      const retrievedUser = JSON.parse(jsonObject);
      this.prenotazioniService.availablePrenotazioni(retrievedUser.token).subscribe({
        next: (data: Prenotazione[]) => {
          this.prenotazioniArray = data;
          console.log(this.prenotazioniArray);
        }
      })
    }
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

  ngOnInit(){
    this.getPrenotazioni();

  }

}
