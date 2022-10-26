package com.klikwplik.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "storages")
@NoArgsConstructor
@AllArgsConstructor
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Column(name = "empty", nullable = false)
    private boolean isEmpty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gang_id")
    private Gang gang;

    public Storage(double longitude, double latitude, boolean isEmpty, Gang gang) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.isEmpty = isEmpty;
        this.gang = gang;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", isEmpty=" + isEmpty +
                ", gang=" + gang.getName() +
                '}';
    }
}
