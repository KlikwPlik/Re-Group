package com.klikwplik.controller;


import com.klikwplik.entity.Member;
import com.klikwplik.exception.GangNotFoundException;
import com.klikwplik.mapper.MemberMapper;
import com.klikwplik.dto.MemberDto;
import com.klikwplik.exception.MemberNotFoundException;
import com.klikwplik.service.GangService;
import com.klikwplik.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    private final GangService gangService;

    public MemberController(MemberService memberService, MemberMapper memberMapper, GangService gangService) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
        this.gangService = gangService;
    }

    @GetMapping("/members")
    public List<MemberDto> all() {
        return memberService.getAll().stream()
                .map(memberMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/members/{id}")
    public MemberDto getOne(@PathVariable Long id) {
        return memberMapper.mapToDto(memberService.getMember(id).orElseThrow(() -> new MemberNotFoundException(id)));
    }

    @PostMapping("gangs/{gangId}/members")
    public ResponseEntity<MemberDto> newMember(@PathVariable Long gangId, @RequestBody MemberDto newMemberDto) {
        Member newMember = gangService.getGang(gangId).map(gang -> {
            Member member = memberMapper.mapToEntity(newMemberDto);
            member.setGang(gang);
            return memberService.saveMember(member);
        }).orElseThrow(() -> new GangNotFoundException(gangId));
        return new ResponseEntity<>(memberMapper.mapToDto(newMember),
                HttpStatus.CREATED);
    }

    @PutMapping("/members/{id}")
    public MemberDto updateMember(@RequestBody MemberDto updatedMember, @PathVariable Long id) {
        return memberMapper.mapToDto(memberService.updateMember(memberMapper.mapToEntity(updatedMember), id));
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable Long id) {
        memberService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
