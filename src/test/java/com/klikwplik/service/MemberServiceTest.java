package com.klikwplik.service;

import com.klikwplik.entity.Member;
import com.klikwplik.exception.MemberAlreadyExists;
import com.klikwplik.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;

    private Member member1;

    @BeforeEach
    void setup() {

    }

    @Test
    void givenMemberObject_whenSaveMember_thenReturnMemberObject() {
        given(memberRepository.save(member)).willReturn(member);
        Member savedMember = memberService.saveMember(member);
        assertThat(savedMember).isNotNull();
    }

    @Test
    void givenExistingMember_whenSaveMember_thenThrowsException() {
        given(memberRepository.findByFirstNameAndLastName(member.getFirstName(), member.getLastName()))
                .willReturn(Optional.of(member));
        assertThrows(MemberAlreadyExists.class, () -> {
            memberService.saveMember(member);
        });
        verify(memberRepository, never()).save(any(Member.class));
    }

    @Test
    void givenMembersList_whenGetAllMembers_thenReturnMembersList() {
        given(memberRepository.findAll()).willReturn(List.of(member,member1));
        List<Member> allMembers = memberService.getAll();
        assertThat(allMembers).isNotNull();
        assertThat(allMembers.size()).isEqualTo(2);
    }

    @Test
    void givenEmptyMembersList_whenGetAllMembers_thenReturnEmptyMembersList() {
        given(memberRepository.findAll()).willReturn(Collections.emptyList());
        List<Member> all = memberService.getAll();
        assertThat(all).isEmpty();
    }

    @Test
    void givenMemberId_whenGetMemberById_thenReturnMemberObject() {
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        Member savedMember = memberService.getMember(member.getId()).get();
        assertThat(savedMember).isNotNull();
    }

    @Test
    void givenMemberObject_whenUpdateMember_thenReturnUpdatedMember() {
        given(memberRepository.save(member1)).willReturn(member1);

        Member updatedMember = memberService.updateMember(member1, 2L);

    }

    @Test
    void givenMemberId_whenDeleteMember_thenMemberDeleted() {
        willDoNothing().given(memberRepository).deleteById(member.getId());
        memberService.deleteById(member.getId());
        verify(memberRepository, times(1)).deleteById(member.getId());
    }
}
