package com.klikwplik.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Resource {

    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long radius;
    private Long amount;
    private String name;
    @Column(name = "coordinates_id")
    private Long coordinatesId;

    public Resource() {
    }

    public Resource(Long radius, Long amount, String name, Long coordinatesId) {
        this.radius = radius;
        this.amount = amount;
        this.name = name;
        this.coordinatesId = coordinatesId;
    }
}
