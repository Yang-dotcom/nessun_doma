package org.example.nessun_doma.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import lombok.Data;


public enum Ruolo{
    CLIENTE, ISTRUTTORE
}
