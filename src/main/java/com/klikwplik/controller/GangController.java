package com.klikwplik.controller;

import com.klikwplik.mapper.GangMapper;
import com.klikwplik.dto.GangDto;
import com.klikwplik.exception.GangAlreadyExists;
import com.klikwplik.exception.GangNotFoundException;
import com.klikwplik.service.GangService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gangs")
public class GangController {

    private final GangService gangService;
    private final GangMapper gangMapper;

    public GangController(GangService gangService, GangMapper gangMapper) {
        this.gangService = gangService;
        this.gangMapper = gangMapper;
    }

    @GetMapping
    public List<GangDto> all() {
        return gangService.getAll()
                .stream().map(gangMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GangDto getOne(@PathVariable Long id) {
        return gangMapper.mapToDto(gangService.getGang(id).orElseThrow(() -> new GangNotFoundException(id)));
    }

    @PostMapping()
    public ResponseEntity<GangDto> newGang(@RequestBody GangDto newGang) throws GangAlreadyExists {
        return new ResponseEntity<>(gangMapper.mapToDto(gangService.saveGang(gangMapper
                .mapToEntity(newGang))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public GangDto updateGang(@RequestBody GangDto updatedGang, @PathVariable Long id) {
        return gangMapper.mapToDto(gangService.updateGang(gangMapper
                .mapToEntity(updatedGang), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGang(@PathVariable Long id) {
        gangService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
