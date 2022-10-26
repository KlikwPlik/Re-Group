package com.klikwplik.config;

import com.klikwplik.entity.*;
import com.klikwplik.repository.*;
import com.klikwplik.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner init(MemberRepository memberRepository
            , GangRepository gangRepository
            , CoordinatesRepository coordinatesRepository
            , ResourceRepository resourceRepository
            , StorageRepository storageRepository) {
        Gang gang = new Gang("Rome", Utils.randomLatitude(), Utils.randomLongitude());
        return (args) -> {
            log.info("Preloading " + gangRepository.save(gang));
            log.info("Preloading " + memberRepository.save(new Member("Klaudiusz", "Lepcis", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
            log.info("Preloading " + memberRepository.save(new Member("Tytus", "Raete", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
            log.info("Preloading " + memberRepository.save(new Member("Decjusz", "Nikopolis", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
            log.info("Preloading " + memberRepository.save(new Member("Walentynian", "Iliria", Utils.randomLatitude(), Utils.randomLongitude(), gang)));
            log.info("Preloading " + storageRepository.save(new Storage(Utils.randomLongitude(), Utils.randomLatitude(), true, gang)));
            loadCoordinates(coordinatesRepository);
            addResourcesToCoordinates(resourceRepository);
        };
    }

    private void loadCoordinates(CoordinatesRepository coordinatesRepository) {
        log.info("Preloading coordinates");
        for (int i = 0; i < 100; i++) {
            coordinatesRepository.save(new Coordinates((long) i, Utils.randomLongitude(), Utils.randomLatitude()));
        }
    }

    private void addResourcesToCoordinates(ResourceRepository resourceRepository) {
        log.info("Adding resources to coordinates");
        for (int i = 0; i < 100; i++) {
            resourceRepository.save(new Resource(10L, ThreadLocalRandom.current().nextLong(), "Money", (long) i));
        }
    }
}
