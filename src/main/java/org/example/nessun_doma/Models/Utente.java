package org.example.nessun_doma.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nessun_doma.Models.Enums.Ruolo;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@JsonIdentityInfo(scope= Utente.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
@NoArgsConstructor
@Slf4j
@AllArgsConstructor
public class Utente{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank(message = "must choose an istructor")
    private String email;
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @Nonnull
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
