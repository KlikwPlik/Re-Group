package com.klikwplik.config;

import com.klikwplik.entity.Member;
import com.klikwplik.repository.MemberRepository;
import com.klikwplik.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner init(MemberRepository repository) {
        return (args) -> {
            log.info("Preloading " + repository.save(new Member("Klaudiusz", "Lepcis", Utils.randomLatitude(), Utils.randomLongitude())));
            log.info("Preloading " + repository.save(new Member("Tytus", "Raete", Utils.randomLatitude(), Utils.randomLongitude())));
            log.info("Preloading " + repository.save(new Member("Decjusz", "Nikopolis", Utils.randomLatitude(), Utils.randomLongitude())));
            log.info("Preloading " + repository.save(new Member("Walentynian", "Iliria", Utils.randomLatitude(), Utils.randomLongitude())));
        };
    }
}
