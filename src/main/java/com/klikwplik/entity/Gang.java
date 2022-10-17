package com.klikwplik.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gang")
    private Set<Member> members = new HashSet<>();
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;

    public Gang(String name, Set<Member> members, double longitude, double latitude) {
        this.name = name;
        this.members = members;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Gang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
