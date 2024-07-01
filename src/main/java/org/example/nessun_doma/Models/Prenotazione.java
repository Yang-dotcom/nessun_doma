package org.example.nessun_doma.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name= "utente_id", nullable = false)
    @JsonBackReference
    private Utente utente;

    @ManyToOne
    @JoinColumn(name= "corso_id", nullable = false)
    @JsonBackReference
    private Corso corso;

    @Column(name = "dataPrenotazione", nullable = false)
    private Date dataPrenotazione;
}
