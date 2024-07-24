package org.example.nessun_doma.Controllers;

import org.example.nessun_doma.DTO.PrenotazioneDTO;
import org.example.nessun_doma.Models.Prenotazione;
import org.example.nessun_doma.Repositories.UtenteRepository;
import org.example.nessun_doma.Security.JwtHelper;
import org.example.nessun_doma.Services.CorsoService;
import org.example.nessun_doma.Services.PrenotazioneService;
import org.example.nessun_doma.Services.UtenteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    private static final Logger log = LoggerFactory.getLogger(PrenotazioneController.class);
    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CorsoService corsoService;

    @PostMapping
    public PrenotazioneDTO createPrenotazione(@RequestBody PrenotazioneDTO prenotazione,  @RequestHeader("Authorization") String authToken) {
        log.info(authToken+ "ciao");
        log.info(prenotazione.getUtenteEmail()+ "ciao");
        log.info(prenotazione.getDataPrenotazione()+ "ciao");
        return convertToDTO(prenotazioneService.upsertPrenotazione(this.convertToEntiyDTO(prenotazione), extractEmailFromToken(authToken)));
    }

    @PutMapping
    public PrenotazioneDTO updatePrenotazione(@RequestBody PrenotazioneDTO prenotazione,  @RequestHeader("Authorization") String authToken) {
        return convertToDTO(prenotazioneService.upsertPrenotazione(this.convertToEntiyDTO(prenotazione), extractEmailFromToken(authToken)));
    }

    @PatchMapping
    public PrenotazioneDTO patchPrenotazione(@RequestBody PrenotazioneDTO prenotazione,  @RequestHeader("Authorization") String authToken) {
        return convertToDTO(prenotazioneService.upsertPrenotazione(this.convertToEntiyDTO(prenotazione), extractEmailFromToken(authToken)));
    }

    @GetMapping
    public List<PrenotazioneDTO> getAllPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni().stream().map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/utente")
    public List<PrenotazioneDTO> getAllUtentePrenotazioni(@RequestHeader("Authorization") String authToken) {
        return prenotazioneService.getAllUtentePrenotazioni(extractEmailFromToken(authToken)).stream().map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public PrenotazioneDTO getPrenotazioneById(@PathVariable Integer id) {
        return this.convertToDTO(prenotazioneService.getPrenotazioneById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Integer id, @RequestHeader("Authorization") String authToken) {
        prenotazioneService.deletePrenotazione(id, extractEmailFromToken(authToken));
    }



    private String extractEmailFromToken(String authHeader){
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = JwtHelper.extractUsername(token);
        }
        return username;
    }

    private PrenotazioneDTO convertToDTO(Prenotazione prenotazione){
        PrenotazioneDTO prenotazioneDTO = modelMapper.map(prenotazione, PrenotazioneDTO.class);
        prenotazioneDTO.builder()
                .dataPrenotazione(prenotazione.getDataPrenotazione())
                .utenteEmail(prenotazione.getUtente().getEmail())
                .id(prenotazione.getId())
                .build();

        //NB: manually setting name of the course because setting it in the builder doesn't work
        prenotazioneDTO.setCorsoName(prenotazione.getCorso().getNome());
        return prenotazioneDTO;
    }

    private Prenotazione convertToEntiyDTO(PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione = modelMapper.map(prenotazioneDTO, Prenotazione.class);
        prenotazione.setDataPrenotazione(prenotazioneDTO.getDataPrenotazione());
        prenotazione.setCorso(corsoService.findCorsoByName(prenotazioneDTO.getCorsoName()));
        prenotazione.setUtente(utenteService.findUserByEmail(prenotazioneDTO.getUtenteEmail()));
        prenotazione.setId(prenotazioneDTO.getId());

        return  prenotazione;
    }



}
