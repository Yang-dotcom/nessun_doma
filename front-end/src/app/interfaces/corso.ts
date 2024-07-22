export interface Corso {
  id: number;
  nome: string;
  availableSpots: number;
  dataInizio: Date;
  dataFine: Date;
  maxPartecipanti: number;
  imgSrc:string;
  istruttore_id:number;
}
