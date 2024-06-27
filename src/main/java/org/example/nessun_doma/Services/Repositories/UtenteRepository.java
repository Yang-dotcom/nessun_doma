package org.example.nessun_doma.Services.Repositories;

import org.example.nessun_doma.Models.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Long> {
    Utente save(Utente utente);

    List<Utente> findAll();

    Optional<Utente> findById(int id);

    void deleteById(int id);
}
