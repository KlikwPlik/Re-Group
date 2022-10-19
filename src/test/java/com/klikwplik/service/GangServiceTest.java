package com.klikwplik.service;

import com.klikwplik.entity.Gang;
import com.klikwplik.entity.Member;
import com.klikwplik.repository.GangRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GangServiceTest {

    @Mock
    private GangRepository gangRepository;

    @InjectMocks
    private GangServiceImpl gangService;

    @Test
    void givenGangId_whenGetGangById_thenReturnGangObject() {
        Gang gang = Gang.builder()
                .id(1L)
                .name("Rome")
                .longitude(37.464267)
                .latitude(15.001517)
                .members(new HashSet<>())
                .build();
        given(gangRepository.findById(1L)).willReturn(Optional.of(gang));
        Optional<Gang> foundGang = gangService.getGang(1L);
        assertThat(foundGang).isNotNull();
    }
}
