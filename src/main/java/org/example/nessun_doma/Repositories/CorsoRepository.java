package org.example.nessun_doma.Repositories;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Models.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CorsoRepository extends CrudRepository<Corso, Integer> {

    Corso save(Corso corso);

    List<Corso> findAll();

    Optional<Corso> findById(int id);

    void deleteById(int id);

    @Query("SELECT DISTINCT p.utente FROM Prenotazione p WHERE p.corso = :corso")
    List<Utente> findDistinctUtenteByCorso(@Param("corso") Corso corso);

    @Query("SELECT c FROM Corso c WHERE c.istruttore_id = :istruttore_id")
    List<Corso> findcorsiByUtente(@Param("istruttore_id") int istruttore_id);


    Optional<Corso> findByNome(String corsoName);
}
