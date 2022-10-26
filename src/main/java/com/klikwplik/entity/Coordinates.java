package com.klikwplik.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Coordinates {
    @Id
    private Long id;
    private double longitude;
    private double latitude;
    @OneToMany
    @JoinColumn(name = "coordinates_id")
    private List<Resource> resource;

    public Coordinates() {
    }

    public Coordinates(Long id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Coordinates(Long id, double longitude, double latitude, List<Resource> resource) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.resource = resource;
    }
}
