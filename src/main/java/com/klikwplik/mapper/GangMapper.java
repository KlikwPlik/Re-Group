package com.klikwplik.mapper;

import com.klikwplik.dto.GangDto;
import com.klikwplik.entity.Gang;
import com.klikwplik.service.MemberService;
import com.klikwplik.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GangMapper {

    private final MemberService memberService;
    private final MemberMapper memberMapper;
    private final StorageService storageService;
    private final StorageMapper storageMapper;

    public GangDto mapToDto(Gang source) {
        Objects.requireNonNull(source, "Source gang can't be null.");
        GangDto result = new GangDto();
        result.setId(source.getId());
        result.setLongitude(source.getLongitude());
        result.setLatitude(source.getLatitude());
        result.setName(source.getName());
        result.setMembers(memberService.findByGangId(source.getId())
                .stream()
                .map(memberMapper::mapToDto)
                .collect(Collectors.toSet()));
        result.setStorages(storageService.findByGangId(source.getId())
                .stream()
                .map(storageMapper::mapToDto)
                .collect(Collectors.toList()));
        return result;
    }

    public Gang mapToEntity(GangDto source) {
        Objects.requireNonNull(source, "Source gang can't be null.");
        Gang result = new Gang();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setLongitude(source.getLongitude());
        result.setLatitude(source.getLatitude());
        return result;
    }
}
