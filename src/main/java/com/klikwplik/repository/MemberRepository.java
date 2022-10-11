package com.klikwplik.repository;

import com.klikwplik.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByLastName(String lastName);

    Member findById(long id);

    Member findByLongitudeAndLatitude(double longitude, double latitude);
}
