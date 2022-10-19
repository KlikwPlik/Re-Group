package com.klikwplik.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GangDto {

    private Long id;
    private double longitude;
    private double latitude;
    private String name;
    private Set<Long> membersIds;
    private Set<String> membersNames;
}
