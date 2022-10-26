package com.klikwplik.mapper;

import com.klikwplik.dto.StorageDto;
import com.klikwplik.entity.Storage;
import com.klikwplik.exception.GangNotFoundException;
import com.klikwplik.service.GangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StorageMapper {

    private final GangService gangService;

    public StorageDto mapToDto(Storage source) {
        StorageDto result = new StorageDto();
        result.setId(source.getId());
        result.setLongitude(source.getLongitude());
        result.setLongitude(source.getLongitude());
        result.setEmpty(source.isEmpty());
        result.setGangId(source.getId());
        return result;
    }

    public Storage mapToEntity(StorageDto source) {
        Storage result = new Storage();
        result.setId(source.getId());
        result.setLongitude(source.getLongitude());
        result.setLongitude(source.getLongitude());
        result.setEmpty(source.isEmpty());
        result.setGang(gangService.findById(source.getGangId()).orElseThrow(() -> new GangNotFoundException(source.getGangId())));
        return result;
    }
}
