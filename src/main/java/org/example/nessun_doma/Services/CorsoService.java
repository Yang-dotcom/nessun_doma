package org.example.nessun_doma.Services;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Services.Repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.example.nessun_doma.Services.Repositories.CorsoRepository;

import java.util.List;

@Service
@ComponentScan(basePackageClasses= CorsoRepository.class)
public class CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    public Corso createCorso(Corso corso) {
        return corsoRepository.save(corso);
    }

    public List<Corso> getAllCorsi() {
        return (List<Corso>) corsoRepository.findAll();
    }

    public Corso getCorsoById(int id) {
        return corsoRepository.findById(id).orElse(null);
    }

    public void deleteCorso(int id) {
        corsoRepository.deleteById(id);
    }
}
