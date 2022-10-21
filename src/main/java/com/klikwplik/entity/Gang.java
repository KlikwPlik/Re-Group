package com.klikwplik.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Builder
@Table(name = "gangs")
@NoArgsConstructor
@AllArgsConstructor
public class Gang {

    @Id
    @Column(name = "gang_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;

    public Gang(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Gang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gang gang = (Gang) o;
        return Double.compare(gang.longitude, longitude) == 0 && Double.compare(gang.latitude, latitude) == 0 && id.equals(gang.id) && name.equals(gang.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, longitude, latitude);
    }
}
