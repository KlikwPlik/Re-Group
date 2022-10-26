package com.klikwplik.repository;

import com.klikwplik.entity.Coordinates;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {

    @Query("select c from Coordinates c"
            + " left join fetch c.resource")
    List<Coordinates> findAllCoordinates();

    @Query("select c from Coordinates c")
    List<Coordinates> findAllCoordinatesPaginated(Pageable page);
}
