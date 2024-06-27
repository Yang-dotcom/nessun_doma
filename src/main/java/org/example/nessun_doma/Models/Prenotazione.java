package org.example.nessun_doma.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int utente_id;
    private int corso_id;
    private Date data_prenotazione;
}
