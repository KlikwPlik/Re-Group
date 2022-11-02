package com.klikwplik.userinterface;

import com.klikwplik.entity.Coordinates;
import com.klikwplik.entity.Gang;
import com.klikwplik.entity.Member;
import com.klikwplik.service.CoordinatesService;
import com.klikwplik.service.MemberService;
import com.klikwplik.service.ResourceService;
import com.klikwplik.service.StorageService;
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
    private Member member2;
    private Coordinates coordinates;

    @BeforeEach
    void setup() {

        coordinates = Coordinates.builder()
                .id(1L)
                .longitude(37.159600)
                .latitude(15.105786)
                .resource(Collections.emptyList())
                .build();
    }

    @Test
    void givenMemberObject_whenMoveMember_thenReturnUpdatedMemberObject() {

        given(memberService.findById(1L)).willReturn(Optional.ofNullable(member));
        given(coordinatesService.findById(1L)).willReturn(Optional.ofNullable(coordinates));
        given(memberService.updateMember(member, member.getId())).willReturn(updatedMember);

        Member moveMember = uiMemberService.moveMember(1L, 1L);

        assertEquals(coordinates.getLongitude(), moveMember.getLongitude());
        assertEquals(coordinates.getLatitude(), moveMember.getLatitude());
    }


}
