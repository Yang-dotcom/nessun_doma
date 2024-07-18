package org.example.nessun_doma.Models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(scope = Corso.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nome;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "istruttore_id", nullable = false, referencedColumnName = "id")
    @Column(name = "istruttore_id", nullable = false)
    @NotNull(message = "must choose an istructor")
    private int istruttore_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "data_inizio", nullable = false)
    private LocalDateTime dataInizio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "data_fine", nullable = false)
    private LocalDateTime dataFine;

    @Column(name = "max_partecipanti", nullable = false)
    private int maxPartecipanti;

    @OneToMany(mappedBy = "corso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "corso-prenotato")
    private List<Prenotazione> prenotazioni = new ArrayList<>();

    @Column(name = "posti_rimasti", nullable = false)
    private Integer availableSpots;

}
