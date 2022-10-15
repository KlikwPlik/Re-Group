package com.klikwplik.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "gangs")
@NoArgsConstructor
public class Gang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Column(name = "name", nullable = false)
    private String name;
}
