package com.klikwplik.service;

import com.klikwplik.entity.Member;
import com.klikwplik.exception.MemberAlreadyExists;
import com.klikwplik.exception.MemberNotFoundException;
import com.klikwplik.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findByLastName(String lastName) {
        return memberRepository.findByLastName(lastName);
    }

    public Optional<Member> getMember(long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByLongitudeAndLatitude(double longitude, double latitude) {
        return memberRepository.findByLongitudeAndLatitude(longitude, latitude);
    }

    @Override
    public Member saveMember(Member member) {
        Optional<Member> retrievedMember = memberRepository.findByFirstNameAndLastName(member.getFirstName(), member.getLastName());
        if (retrievedMember.isPresent()) {
            throw new MemberAlreadyExists(member.getFirstName(), member.getLastName());
        }
        return memberRepository.save(member);
    }

    public Member updateMember(Member updatedMember, Long id) {
        return memberRepository.findById(id)
                .map(retrievedMember -> {
                    retrievedMember.setFirstName(updatedMember.getFirstName());
                    retrievedMember.setLastName(updatedMember.getLastName());
                    retrievedMember.setLongitude(updatedMember.getLongitude());
                    retrievedMember.setLatitude(updatedMember.getLatitude());
                    return memberRepository.save(updatedMember);
                })
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> findByGangId(long id) {
        return memberRepository.findByGangId(id);
    }

    @Override
    public void deleteByGangId(long id) {
        memberRepository.deleteByGangId(id);
    }

    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public List<Member> reGroup(List<Member> members, double longitude, double latitude) {
        for (Member member : members) {
            member.setLongitude(longitude);
            member.setLatitude(latitude);
            memberRepository.save(member);
        }
        return members;
    }
}
