package com.klikwplik.conventer;

import com.klikwplik.dto.MemberDto;
import com.klikwplik.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MemberConverter implements Converter<Member, MemberDto> {
    @Override
    public MemberDto convert(Member source) {
        Objects.requireNonNull(source, "Source member can't be null.");
        MemberDto memberDto = new MemberDto();
        memberDto.setId(source.getId());
        memberDto.setFirstName(source.getFirstName());
        memberDto.setLastName(source.getLastName());
        memberDto.setLatitude(source.getLatitude());
        memberDto.setLongitude(source.getLongitude());
        return memberDto;
    }

    public List<MemberDto> convertList(List<Member> members) {
        Objects.requireNonNull(members, "Source members list can't be null.");
        return members
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
