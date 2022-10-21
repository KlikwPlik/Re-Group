package com.klikwplik.repository;

import com.klikwplik.entity.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByLastName(String lastName);

    Optional<Member> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Member> findById(long id);

    Optional<Member> findByLongitudeAndLatitude(double longitude, double latitude);

    Optional<Member> findByLongitude(double longitude, Sort sort);

    Optional<Member> findByLatitude(double longitude, Sort sort);

    List<Member> findByGangId(long id);

    @Transactional
    void deleteByGangId(long id);

}
