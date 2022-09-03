package io.study.security_basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SecurityBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityBasicApplication.class, args);
    }

}
