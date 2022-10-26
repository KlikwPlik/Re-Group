package com.klikwplik.userinterface;

import com.klikwplik.dto.MemberDto;
import com.klikwplik.entity.*;
import com.klikwplik.exception.CoordinatesNotFoundException;
import com.klikwplik.exception.MemberNotFoundException;
import com.klikwplik.exception.ResourceNotFoundException;
import com.klikwplik.exception.StorageNotFoundException;
import com.klikwplik.mapper.MemberMapper;
import com.klikwplik.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/front")
@RequiredArgsConstructor
public class UiMemberController {

    private final UiMemberService uiMemberService;
    private final MemberMapper memberMapper;

    @PostMapping("/members/{id}/to-coordinates/{coordinatesId}")
    public MemberDto moveMember(@PathVariable Long id, @PathVariable Long coordinatesId) {
        return memberMapper.mapToDto(uiMemberService.moveMember(id, coordinatesId));
    }

    @PostMapping("/members/{memberId}/bring-from-coordinates/{coordinatesId}")
    public MemberDto bringResourcesToGangStorage(@PathVariable Long memberId, @PathVariable Long coordinatesId) {
        return memberMapper.mapToDto(uiMemberService.bringResourcesToGangStorage(memberId, coordinatesId));
    }
}
