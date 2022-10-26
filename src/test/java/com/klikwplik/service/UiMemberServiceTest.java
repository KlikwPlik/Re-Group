package com.klikwplik.service;

import com.klikwplik.entity.Coordinates;
import com.klikwplik.entity.Gang;
import com.klikwplik.entity.Member;
import com.klikwplik.userinterface.UiMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UiMemberServiceTest {

    @InjectMocks
    UiMemberService uiMemberService;

    @Mock
    private MemberService memberService;
    @Mock
    private CoordinatesService coordinatesService;
    @Mock
    private ResourceService resourceService;
    @Mock
    private StorageService storageService;

    private Gang gang;
    private Member member;
    private Member member1;
    private Coordinates coordinates;

    @BeforeEach
    void setup() {
        gang = new Gang(1L, "Rome", 37.559180, 15.175465);
        member = Member.builder()
                .id(1L)
                .firstName("Probus")
                .lastName("Sirmium")
                .longitude(37.459380)
                .latitude(15.082465)
                .gang(gang)
                .build();
        member1 = Member.builder()
                .id(2L)
                .firstName("Aurelian")
                .lastName("Palmyry")
                .longitude(37.535868)
                .latitude(15.002046)
                .gang(gang)
                .build();
        coordinates = Coordinates.builder()
                .id(1L)
                .longitude(37.159600)
                .latitude(15.105786)
                .resource(Collections.emptyList())
                .build();
    }

    @Test
    void givenMemberObject_whenMoveMember_thenReturnUpdatedMemberObject() {
        Member updatedMember = Member.builder()
                .id(1L)
                .firstName("Probus")
                .lastName("Sirmium")
                .longitude(coordinates.getLongitude())
                .latitude(coordinates.getLatitude())
                .gang(gang)
                .build();
        given(memberService.findById(1L)).willReturn(Optional.ofNullable(member));
        given(coordinatesService.findById(1L)).willReturn(Optional.ofNullable(coordinates));
        given(memberService.updateMember(member, member.getId())).willReturn(updatedMember);

        memberService.updateMember(member, member.getId());

        Member moveMember = uiMemberService.moveMember(1L, 1L);
        assertEquals(coordinates.getLongitude(), moveMember.getLongitude());
        assertEquals(coordinates.getLatitude(), moveMember.getLatitude());
    }


}
