package com.klikwplik.config;

import com.klikwplik.entity.Gang;
import com.klikwplik.entity.Member;
import com.klikwplik.repository.GangRepository;
import com.klikwplik.repository.MemberRepository;
import com.klikwplik.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner init(MemberRepository memberRepository, GangRepository gangRepository) {
        Gang gang = new Gang("Rome", Set.of(new Member("Neron", "Domusaurera", Utils.randomLongitude(), Utils.randomLatitude())), Utils.randomLatitude(), Utils.randomLongitude());
        return (args) -> {
            log.info("Preloading " + gangRepository.save(gang));
            log.info("Preloading " + memberRepository.save(new Member("Klaudiusz", "Lepcis", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
            log.info("Preloading " + memberRepository.save(new Member("Tytus", "Raete", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
            log.info("Preloading " + memberRepository.save(new Member("Decjusz", "Nikopolis", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
            log.info("Preloading " + memberRepository.save(new Member("Walentynian", "Iliria", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
        };
    }
}
