import {Component, inject, Input, input, OnInit} from '@angular/core';
import {CorsiServiceService} from "../../services/corsiService/corsi-service.service";
import {Corso} from "../../interfaces/corso";
import {HomeClientComponent} from "../home-client/home-client.component";

@Component({
  selector: 'app-corsi',
  standalone: true,
  imports: [
    HomeClientComponent
  ],
  templateUrl: './corsi.component.html',
  styleUrl: './corsi.component.css'
})


export class CorsiComponent {
  corsiService = inject(CorsiServiceService)
/*
  corsi: Item[] = [];

  public getCorsi(){
    this.corsiService.availableCourses()
      .subscribe({
      next: (data:Item[]) => {
        this.corsi = data;
        console.log(data)
      }
    });
  }

  ngOnInit() {
    this.getCorsi()
  }
*/


}
