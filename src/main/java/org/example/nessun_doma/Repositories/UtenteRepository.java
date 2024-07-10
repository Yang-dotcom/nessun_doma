package org.example.nessun_doma.Repositories;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long> {
    Utente save(Utente utente);

    List<Utente> findAll();

    Optional<Utente> findById(int id);

    void deleteById(int id);

    @Query("SELECT DISTINCT p.corso FROM Prenotazione p WHERE p.utente = :utente")
    List<Corso> findDistinctCorsoByUtente(@Param("utente") Utente utente);

    Optional<Utente> findByEmail(String email);
}
