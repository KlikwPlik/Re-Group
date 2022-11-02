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
    @Enumerated(EnumType.STRING)
    private ResourceName resourceName;
    @Column(name = "coordinates_id")
    private Long coordinatesId;

    public Resource() {
    }

    public Resource(Long radius, Long amount, ResourceName resourceName, Long coordinatesId) {
        this.radius = radius;
        this.amount = amount;
        this.resourceName = resourceName;
        this.coordinatesId = coordinatesId;
    }
}
