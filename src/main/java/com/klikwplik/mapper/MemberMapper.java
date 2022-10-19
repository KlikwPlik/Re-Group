package com.klikwplik.mapper;

import com.klikwplik.dto.MemberDto;
import com.klikwplik.entity.Member;
import com.klikwplik.exception.GangNotFoundException;
import com.klikwplik.service.GangService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MemberMapper {

    GangService gangService;

    public MemberMapper(GangService gangService) {
        this.gangService = gangService;
    }

    public MemberDto mapToDto(Member source) {
        Objects.requireNonNull(source, "Source member can't be null.");
        MemberDto result = new MemberDto();
        result.setId(source.getId());
        result.setFirstName(source.getFirstName());
        result.setLastName(source.getLastName());
        result.setLongitude(source.getLongitude());
        result.setLatitude(source.getLatitude());
        result.setGangId(source.getGang().getId());
        result.setGangName(source.getGang().getName());
        return result;
    }

    public Member mapToEntity(MemberDto source) {
        Objects.requireNonNull(source, "Source member can't be null.");
        Member result = new Member();
        result.setId(source.getId());
        result.setFirstName(source.getFirstName());
        result.setLastName(source.getLastName());
        result.setLongitude(source.getLongitude());
        result.setLatitude(source.getLatitude());
        result.setGang(gangService.findById(source.getGangId()).orElseThrow(() -> new GangNotFoundException(source.getGangId())));
        return result;
    }


}

