package com.klikwplik.controller;

import com.klikwplik.conventer.MemberConverter;
import com.klikwplik.dto.MemberDto;
import com.klikwplik.entity.Member;
import com.klikwplik.exception.MemberNotFoundException;
import com.klikwplik.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    public MemberController(MemberService memberService, MemberConverter memberConverter) {
        this.memberService = memberService;
        this.memberConverter = memberConverter;
    }

    @GetMapping
    public List<MemberDto> all() {
        return memberConverter.convertList(memberService.getAll());
    }

    @GetMapping("/{id}")
    public MemberDto getOne(@PathVariable Long id) {
        return memberConverter.convert(memberService.getMember(id).orElseThrow(() -> new MemberNotFoundException(id)));
    }

    @PostMapping()
    public ResponseEntity<MemberDto> newMember(@RequestBody Member newMember) {
        return new ResponseEntity<>(memberConverter.convert(memberService.saveMember(newMember)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public MemberDto updateMember(@RequestBody Member updatedMember, @PathVariable Long id) {
        return memberConverter.convert(memberService.updateMember(updatedMember, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable Long id) {
        memberService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
