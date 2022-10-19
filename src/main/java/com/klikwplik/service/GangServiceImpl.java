package com.klikwplik.service;

import com.klikwplik.entity.Gang;
import com.klikwplik.exception.GangAlreadyExists;
import com.klikwplik.repository.GangRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GangServiceImpl implements GangService {

    private final GangRepository gangRepository;
    private final MemberService memberService;

    public GangServiceImpl(GangRepository gangRepository, MemberService memberService) {
        this.gangRepository = gangRepository;
        this.memberService = memberService;
    }

    @Override
    public List<Gang> getAll() {
        return gangRepository.findAll();
    }

    @Override
    public Optional<Gang> findByName(String name) {
        return gangRepository.findByName(name);
    }

    @Override
    public Optional<Gang> getGang(long id) {
        return gangRepository.findById(id);
    }

    @Override
    public Optional<Gang> findByLongitudeAndLatitude(double longitude, double latitude) {
        return gangRepository.findByLongitudeAndLatitude(longitude, latitude);
    }

    @Override
    public Gang saveGang(Gang gang) throws GangAlreadyExists {
        Optional<Gang> retrievedGang = gangRepository.findByName(gang.getName());
        if (retrievedGang.isPresent()) {
            throw new GangAlreadyExists(gang.getName());
        }
        return gangRepository.save(gang);
    }

    @Override
    public Gang updateGang(Gang updatedGang, Long id) {
        return gangRepository.findById(id)
                .map(retrievedGang -> {
                    retrievedGang.setId(id);
                    retrievedGang.setName(updatedGang.getName());
                    retrievedGang.setLongitude(updatedGang.getLongitude());
                    retrievedGang.setLatitude(updatedGang.getLatitude());
                    return gangRepository.save(retrievedGang);
                })
                .orElseGet(() -> {
                    updatedGang.setId(id);
                    return gangRepository.save(updatedGang);
                });
    }

    @Override
    public Optional<Gang> findById(Long gangId) {
        return gangRepository.findById(gangId);
    }

    @Override
    public void deleteById(Long id) {
        gangRepository.deleteById(id);
    }
}
