package org.example.nessun_doma.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Utente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String cognome;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @PostLoad
    private void postLoad() {
        // Convert the role to uppercase after loading from the database
        if (this.ruolo != null) {
            this.ruolo = Ruolo.valueOf(this.ruolo.name().toUpperCase());
        }
    }

    @OneToMany(mappedBy = "utente", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "utente-prenota")
    private List<Prenotazione> prenotazioni = new ArrayList<>();


    //@OneToMany(mappedBy = "istruttore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Corso> istruttoreCorsi;


}
