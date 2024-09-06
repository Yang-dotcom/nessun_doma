package org.example.nessun_doma.Controllers;

import org.example.nessun_doma.Models.Corso;
import org.example.nessun_doma.Models.Utente;
import org.example.nessun_doma.Security.JwtHelper;
import org.example.nessun_doma.Services.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/corsi")

public class CorsoController {


    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.final-dir}")
    private String finalDir;

    private CorsoService corsoService;



    @Autowired
    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @PostMapping
    public Corso createCorso(@RequestBody Corso corso, @RequestHeader("Authorization") String authToken) {
        return corsoService.upsertCorso(corso, extractEmailFromToken(authToken));
    }

    @PutMapping
    public Corso updatePrenotazione(@RequestBody Corso corso,  @RequestHeader("Authorization") String authToken) {
        return corsoService.upsertCorso(corso, extractEmailFromToken(authToken));
    }

    @PatchMapping
    public Corso patchPrenotazione(@RequestBody Corso corso,  @RequestHeader("Authorization") String authToken) {
        return corsoService.upsertCorso(corso, extractEmailFromToken(authToken));
    }

    @GetMapping
    public List<Corso> getAllCorsi() {
        return corsoService.getAllCorsi();
    }

    @GetMapping("/available")
    public List<Corso> getAvailableCoursi(){
        return corsoService.getHasFreeSpotsCourses();
    }

    @GetMapping("/istruttore")
    public List<Corso> getAllIstruttoreCorsi(@RequestHeader("Authorization") String authToken) {
        return corsoService.getAllIstruttoreCorsi(extractEmailFromToken(authToken));
    }

    @GetMapping("/{id}")
    public Corso getCorsoById(@PathVariable Integer id) {
        return corsoService.getCorsoById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Integer id, @RequestHeader("Authorization") String authToken) {
        corsoService.deleteCorso(id, extractEmailFromToken(authToken));
    }

    @GetMapping("/utenti/{corso_id}")
    public List<Utente> getUtentiByCorso(@PathVariable Long corso_id) {
        Corso corso = new Corso();
        corso.setId(Math.toIntExact(corso_id));
        return corsoService.findUtentiByCorso(corso);
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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("No file selected for upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Save file temporarily
            Path tempPath = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
            Files.createDirectories(tempPath.getParent());
            Files.write(tempPath, file.getBytes());

            // Return the file path to the front-end
            return new ResponseEntity<>(tempPath.toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not upload the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitForm(@RequestParam("filePath") String filePath) {
        try {
            // Convert file path to Path object
            Path tempPath = Paths.get(filePath);
            Path finalPath = Paths.get(finalDir + File.separator + tempPath.getFileName());

            // Ensure the final directory exists
            Files.createDirectories(finalPath.getParent());

            // Move the file to the final directory
            Files.move(tempPath, finalPath);

            // Delete the file from the upload directory after moving
            Files.deleteIfExists(tempPath);

            return new ResponseEntity<>("File moved successfully to " + finalPath.toString(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not move the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
