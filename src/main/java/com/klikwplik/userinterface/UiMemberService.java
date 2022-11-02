package com.klikwplik.userinterface;

import com.klikwplik.entity.*;
import com.klikwplik.exception.CoordinatesNotFoundException;
import com.klikwplik.exception.MemberNotFoundException;
import com.klikwplik.exception.ResourceNotFoundException;
import com.klikwplik.exception.StorageNotFoundException;
import com.klikwplik.service.CoordinatesService;
import com.klikwplik.service.MemberService;
import com.klikwplik.service.ResourceService;
import com.klikwplik.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UiMemberService {

    private final MemberService memberService;
    private final CoordinatesService coordinatesService;
    private final ResourceService resourceService;
    private final StorageService storageService;

    public Member moveMember(Long id, Long coordinatesId) {
        Member member = memberService.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        Coordinates coordinates = coordinatesService.findById(coordinatesId).orElseThrow(() -> new CoordinatesNotFoundException(coordinatesId));
        return move(member, coordinates.getLongitude(), coordinates.getLatitude());
    }

    public Member bringResourcesToGangStorage(Long memberId, Long coordinatesId) {
        Member member = memberService.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        Coordinates coordinates = coordinatesService.findById(coordinatesId).orElseThrow(() -> new CoordinatesNotFoundException(coordinatesId));
        move(member, coordinates);
        Resource resource = resourceService.findByCoordinatesId(coordinates.getId()).orElseThrow(() -> new ResourceNotFoundException(coordinates));
        resourceService.deleteById(resource.getId());
        Gang gang = member.getGang();
        Storage storage = storageService.findByGangId
                (gang.getId()).stream().findFirst().orElseThrow(() -> new StorageNotFoundException(gang.getId()));
        move(member, storage.getLongitude(), storage.getLatitude());
        storage.setEmpty(false);
        storageService.saveStorage(storage);
        return member;
    }

    private Member move(Member member, Coordinates coordinates) {
        member.setLatitude(coordinates.getLatitude());
        member.setLongitude(coordinates.getLongitude());
        return memberService.updateMember(member, member.getId());
    }

    private Member move(Member member, double longitude, double latitude) {
        member.setLongitude(longitude);
        member.setLatitude(latitude);
        return memberService.updateMember(member, member.getId());
    }
}
