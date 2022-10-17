package com.klikwplik.controller;

import com.klikwplik.conventer.GangConverter;
import com.klikwplik.dto.GangDto;
import com.klikwplik.entity.Gang;
import com.klikwplik.exception.GangAlreadyExists;
import com.klikwplik.exception.GangNotFoundException;
import com.klikwplik.service.GangService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gangs")
public class GangController {

    private final GangService gangService;
    private final GangConverter gangConverter;

    public GangController(GangService gangService, GangConverter gangConverter) {
        this.gangService = gangService;
        this.gangConverter = gangConverter;
    }

    @GetMapping
    public List<GangDto> all() {
        return gangConverter.convertList(gangService.getAll());
    }

    @GetMapping("/{id}")
    public GangDto getOne(@PathVariable Long id) {
        return gangConverter.convert(gangService.getGang(id).orElseThrow(() -> new GangNotFoundException(id)));
    }

    @PostMapping()
    public ResponseEntity<GangDto> newGang(@RequestBody Gang newGang) throws GangAlreadyExists {
        return new ResponseEntity<>(gangConverter.convert(gangService.saveGang(newGang)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public GangDto updateGang(@RequestBody Gang updatedGang, @PathVariable Long id) {
        return gangConverter.convert(gangService.updateGang(updatedGang, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGang(@PathVariable Long id) {
        gangService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
