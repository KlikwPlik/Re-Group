package com.klikwplik.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageDto {

    private Long id;
    private double longitude;
    private double latitude;
    private boolean isEmpty;
    private long gangId;
}
