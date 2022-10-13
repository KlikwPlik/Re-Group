package com.klikwplik.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    private Long id;
    private String firstName;
    private String lastName;
    private double longitude;
    private double latitude;
}
