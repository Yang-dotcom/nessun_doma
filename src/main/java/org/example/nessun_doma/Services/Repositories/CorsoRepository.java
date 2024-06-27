package org.example.nessun_doma.Services.Repositories;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorsoRepository extends CrudRepository<Corso, Integer> {

    Corso save(Corso corso);

    List<Corso> findAll();

    Optional<Corso> findById(int id);

    void deleteById(int id);
}
