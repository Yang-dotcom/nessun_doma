package org.example.nessun_doma.Repositories;

import org.example.nessun_doma.Models.Prenotazione;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Integer> {

    Prenotazione save(Prenotazione prenotazione);

    List<Prenotazione> findAll();

    Optional<Prenotazione> findById(int id);

    void deleteById(int id);



}
