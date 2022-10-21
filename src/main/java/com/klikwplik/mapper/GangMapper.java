package com.klikwplik.mapper;

import com.klikwplik.dto.GangDto;
import com.klikwplik.entity.Gang;
import com.klikwplik.service.MemberService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GangMapper {

    private final MemberService memberService;

    private final MemberMapper memberMapper;

    public GangMapper(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

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
