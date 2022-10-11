package com.klikwplik.controller;

import com.klikwplik.entity.Member;
import com.klikwplik.exception.MemberNotFoundException;
import com.klikwplik.repository.MemberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    List<Member> all() {
        return memberRepository.findAll();
    }

    @GetMapping("/members/{id}")
    Member getOne(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
    }

    @PostMapping("/members")
    Member newMember(@RequestBody Member newMember) {
        return memberRepository.save(newMember);
    }

    @PutMapping("/members/{id}")
    Member updateMember(@RequestBody Member updatedMember, @PathVariable Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setFirstName(updatedMember.getFirstName());
                    member.setLastName(updatedMember.getLastName());
                    member.setLongitude(updatedMember.getLongitude());
                    member.setLatitude(updatedMember.getLatitude());
                    return memberRepository.save(updatedMember);
                })
                .orElseGet(() -> {
                    updatedMember.setId(id);
                    return memberRepository.save(updatedMember);
                });
    }

    @DeleteMapping("/members/{id}")
    void deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
    }
}
