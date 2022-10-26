package com.klikwplik.repository;

import com.klikwplik.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Optional<Resource> findByCoordinatesId(long id);

}
