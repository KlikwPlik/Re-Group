package com.klikwplik.conventer;

import com.klikwplik.dto.GangDto;
import com.klikwplik.entity.Gang;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GangConverter implements Converter<Gang, GangDto> {

    @Override
    public GangDto convert(Gang source) {
        Objects.requireNonNull(source, "Source gang can't be null.");
        GangDto gangDto = new GangDto();
        gangDto.setId(source.getId());
        gangDto.setName(source.getName());
        gangDto.setMembers(source.getMembers());
        gangDto.setLatitude(source.getLatitude());
        gangDto.setLongitude(source.getLongitude());
        return gangDto;
    }

    public List<GangDto> convertList(List<Gang> gangs) {
        Objects.requireNonNull(gangs, "Source gangs list can't be null.");
        return gangs
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
