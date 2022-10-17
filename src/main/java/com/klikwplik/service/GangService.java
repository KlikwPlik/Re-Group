package com.klikwplik.service;

import com.klikwplik.entity.Gang;
import com.klikwplik.exception.GangAlreadyExists;

import java.util.List;
import java.util.Optional;

public interface GangService {

    public List<Gang> getAll();

    Optional<Gang> findByName(String name);

    Optional<Gang> getGang(long id);

    Optional<Gang> findByLongitudeAndLatitude(double longitude, double latitude);

    Gang saveGang(Gang gang) throws GangAlreadyExists;

    Gang updateGang(Gang gang, Long id);

    void deleteById(Long id);
}
