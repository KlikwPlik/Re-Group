package com.klikwplik.controller;

import com.klikwplik.entity.Coordinates;
import com.klikwplik.service.CoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coordinates")
@RequiredArgsConstructor
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    @GetMapping
    public List<Coordinates> findAll() {
        return coordinatesService.findAllCoordinates();
    }

    @GetMapping("/page/{page}")
    public List<Coordinates> findAllPaginated(@PathVariable int page) {
        return coordinatesService.findPaginatedCoordinates(page,10);
    }
}
