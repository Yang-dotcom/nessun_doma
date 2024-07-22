package org.example.nessun_doma.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(scope = Prenotazione.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Prenotazione implements Serializable {

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "dataPrenotazione", nullable = false)
    private LocalDateTime dataPrenotazione;
}
