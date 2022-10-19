package com.klikwplik.controller;


import com.klikwplik.mapper.MemberMapper;
import com.klikwplik.dto.MemberDto;
import com.klikwplik.exception.MemberNotFoundException;
import com.klikwplik.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping
    public List<MemberDto> all() {
        return memberService.getAll().stream()
                .map(memberMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MemberDto getOne(@PathVariable Long id) {
        return memberMapper.mapToDto(memberService.getMember(id).orElseThrow(() -> new MemberNotFoundException(id)));
    }

    @PostMapping()
    public ResponseEntity<MemberDto> newMember(@RequestBody MemberDto newMember) {
        return new ResponseEntity<>(memberMapper.mapToDto(memberService.saveMember(memberMapper.mapToEntity(newMember))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public MemberDto updateMember(@RequestBody MemberDto updatedMember, @PathVariable Long id) {
        return memberMapper.mapToDto(memberService.updateMember(memberMapper.mapToEntity(updatedMember), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable Long id) {
        memberService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
