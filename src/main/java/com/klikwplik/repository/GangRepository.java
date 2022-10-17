package com.klikwplik.repository;

import com.klikwplik.entity.Gang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GangRepository extends JpaRepository<Gang, Long> {

    Optional<Gang> findByLongitudeAndLatitude(double longitude, double latitude);

    Optional<Gang> findByName(String name);

    Optional<Gang> findById(long id);
}
