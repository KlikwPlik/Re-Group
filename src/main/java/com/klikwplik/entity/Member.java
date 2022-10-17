package com.klikwplik.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gang_id", nullable = true)
    private Gang gang;

    public Member(String firstName, String lastName, double longitude, double latitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Member(String firstName, String lastName, double longitude, double latitude, Gang gang) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.gang = gang;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", longitude=" + limitDigits(longitude) +
                ", latitude=" + limitDigits(latitude) +
                 ", gang=" + getGang() +
                '}';
    }

    private String getGang() {
        return gang != null ? gang.getName() : "In a gang unknown!";
    }

    private String limitDigits(double number ) {
        return String.format("%.6f",number);
    }
}
