package org.example.nessun_doma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.example.nessun_doma.Models")
//@EnableJpaRepositories("org.example.nessun_doma.Services.Repositories")

public class NessunDomaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NessunDomaApplication.class, args);
    }

}
