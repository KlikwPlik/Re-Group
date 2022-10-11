package com.klikwplik.service;

import com.klikwplik.entity.Member;
import com.klikwplik.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
