package org.example.nessun_doma.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Entity
@Data
public class Prenotazione implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name= "utente_id", nullable = false, referencedColumnName = "id")

    private Utente utente;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name= "corso_id", nullable = false)

    private Corso corso;

    @Column(name = "dataPrenotazione", nullable = false)
    private Date dataPrenotazione;
}
