package org.example.nessun_doma.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Entity
@Data
public class Prenotazione implements Serializable {

    public Prenotazione() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "utente-prenota")
    @JoinColumn(name= "utente_id", nullable = false, referencedColumnName = "id")
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "corso-prenotato")
    @JoinColumn(name= "corso_id", nullable = false)
    @Nonnull
    private Corso corso;

    @Column(name = "dataPrenotazione", nullable = false)
    private Date dataPrenotazione;

}
