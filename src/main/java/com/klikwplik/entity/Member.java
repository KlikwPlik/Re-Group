package com.klikwplik.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private double longitude;
    private double latitude;

    protected Member() {}

    public Member(String firstName, String lastName, double longitude, double latitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", longitude=" + limitDigits(longitude) +
                ", latitude=" + limitDigits(latitude) +
                '}';
    }

    private String limitDigits(double number ) {
        return String.format("%.6f",number);
    }
}
