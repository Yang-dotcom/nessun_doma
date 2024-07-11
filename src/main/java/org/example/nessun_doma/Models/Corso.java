package org.example.nessun_doma.Models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "istruttore_id", nullable = false, referencedColumnName = "id")
    @Column(name = "istruttore_id", nullable = false)
    private int istruttore_id;
    @Column(name = "data_inizio", nullable = false)
    private Date dataInizio;
    @Column(name = "data_fine", nullable = false)
    private Date dataFine;
    @Column(name = "max_partecipanti", nullable = false)
    private int maxPartecipanti;

    @OneToMany(mappedBy = "corso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "corso-prenotato")
    private List<Prenotazione> prenotazioni = new ArrayList<>();

}
