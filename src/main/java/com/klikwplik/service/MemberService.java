package com.klikwplik.service;

import com.klikwplik.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public List<Member> getAll();

    public List<Member> reGroup(List<Member> members, double longitude, double latitude);
    List<Member> findByLastName(String lastName);
    Optional<Member> getMember(long id);
    Optional<Member> findByLongitudeAndLatitude(double longitude, double latitude);

    Member saveMember(Member member);

    Member updateMember(Member member, Long id);

    void deleteById(Long id);

    Optional<Member> findById(Long id);

    List<Member> findByGangId(long id);

    void deleteByGangId(long id);
}
