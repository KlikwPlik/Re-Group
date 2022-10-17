package com.klikwplik.dto;

import com.klikwplik.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GangDto {

    private Long id;
    private double longitude;
    private double latitude;
    private String name;
    private Set<Member> members = new HashSet<>();
}
