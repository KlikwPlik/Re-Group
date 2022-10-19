package com.klikwplik.mapper;

import com.klikwplik.dto.GangDto;
import com.klikwplik.entity.Gang;

import com.klikwplik.entity.Member;
import com.klikwplik.service.MemberService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GangMapper {

    private final MemberService memberService;

    public GangMapper(MemberService memberService) {
        this.memberService = memberService;
    }

    public GangDto mapToDto(Gang source) {
        Objects.requireNonNull(source, "Source gang can't be null.");
        GangDto result = new GangDto();
        result.setId(source.getId());
        result.setLongitude(source.getLongitude());
        result.setLatitude(source.getLatitude());
        result.setName(source.getName());
        result.setMembersIds(source.getMembers().stream().map(Member::getId).collect(Collectors.toSet()));
        result.setMembersNames(source.getMembers().stream().map(member -> member.getFirstName() + " " + member.getLastName()).collect(Collectors.toSet()));
        return result;
    }

    public Gang mapToEntity(GangDto source) {
        Objects.requireNonNull(source, "Source gang can't be null.");
        Gang result = new Gang();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setMembers(source.getMembersIds().stream().map(memberService::findById).map(Optional::orElseThrow).collect(Collectors.toSet()));
        result.setLongitude(source.getLongitude());
        result.setLatitude(source.getLatitude());
        return result;
    }
}
