package com.klikwplik.service;

import com.klikwplik.entity.Coordinates;
import com.klikwplik.repository.CoordinatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;

    public List<Coordinates> findAllCoordinates() {
        return coordinatesRepository.findAllCoordinates();
    }

    public Optional<Coordinates> findById(long id) {
        return coordinatesRepository.findById(id);
    }

    public List<Coordinates> findPaginatedCoordinates(int page, int size) {
        return coordinatesRepository.findAllCoordinatesPaginated(PageRequest.of(page, size));
    }

}
