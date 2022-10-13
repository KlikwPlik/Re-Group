package com.klikwplik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klikwplik.entity.Member;
import com.klikwplik.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllMembersTest() throws Exception {
        Member member1 = Member.builder()
                .id(2L)
                .firstName("Tytus")
                .lastName("Raete")
                .longitude(37.535868)
                .latitude(15.002046)
                .build();
        Member member2 = Member.builder()
                .id(1L)
                .firstName("Klaudiusz")
                .lastName("Lepcis")
                .longitude(37.425392)
                .latitude(15.012435)
                .build();
        given(service.getAll()).willReturn(List.of(member1, member2));
        mvc.perform(get("/api/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andDo(print());
    }

    @Test
    public void shouldReturnOneMemberTest() throws Exception {
        Member member1 = Member.builder()
                .id(2L)
                .firstName("Tytus")
                .lastName("Raete")
                .longitude(37.535868)
                .latitude(15.002046)
                .build();
        given(service.getMember(member1.getId())).willReturn(Optional.of(member1));
        mvc.perform(get("/api/members/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(member1.getFirstName())))
                .andDo(print());
    }

    @Test
    public void shouldReturnNotFoundMember() throws Exception {
        when(service.getMember(1L)).thenReturn(Optional.empty());
        mvc.perform(get("/api/members/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateMember() throws Exception {
        Member member2 = Member.builder()
                .id(1L)
                .firstName("Klaudiusz")
                .lastName("Lepcis")
                .longitude(37.425392)
                .latitude(15.012435)
                .build();
        when(service.saveMember(member2)).thenReturn(member2);
        mvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member2)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldUpdateMember() throws Exception {
        Member member1 = Member.builder()
                .id(2L)
                .firstName("Tytus")
                .lastName("Raete")
                .longitude(37.535868)
                .latitude(15.002046)
                .build();
        Member updatedMember1 = Member.builder()
                .id(2L)
                .firstName("Mesi")
                .lastName("Vesuvpesincend")
                .longitude(37.535878)
                .latitude(15.002046)
                .build();
        when(service.getMember(2L)).thenReturn(Optional.of(member1));
        when(service.updateMember(updatedMember1, 2L)).thenReturn(updatedMember1);
        mvc.perform(put("/api/members/2").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMember1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(updatedMember1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(updatedMember1.getLastName()))
                .andExpect(jsonPath("$.longitude").value(updatedMember1.getLongitude()))
                .andExpect(jsonPath("$.latitude").value(updatedMember1.getLatitude()))
                .andDo(print());
    }

    @Test
    public void shouldDeleteMember() throws Exception {
        doNothing().when(service).deleteById(1L);
        mvc.perform(delete("/api/members/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}