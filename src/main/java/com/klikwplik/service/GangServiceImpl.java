package com.klikwplik.service;

import com.klikwplik.entity.Gang;
import com.klikwplik.entity.Member;
import com.klikwplik.exception.GangAlreadyExists;
import com.klikwplik.exception.GangNotFoundException;
import com.klikwplik.repository.GangRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GangServiceImpl implements GangService {

    private final GangRepository gangRepository;

    public GangServiceImpl(GangRepository gangRepository) {
        this.gangRepository = gangRepository;
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
                    retrievedGang.setName(updatedGang.getName());
                    retrievedGang.setLongitude(updatedGang.getLongitude());
                    retrievedGang.setLatitude(updatedGang.getLatitude());
                    retrievedGang.setMembers(updatedGang.getMembers());
                    return gangRepository.save(updatedGang);
                })
                .orElseGet(() -> {
                    updatedGang.setId(id);
                    return gangRepository.save(updatedGang);
                });
    }

    public Gang addMemberToGang(Long gangId, Member member) {
        return getGang(gangId).map(gang -> {
            Set<Member> gangMembers = gang.getMembers();
            gangMembers.add(member);
            gang.setMembers(gangMembers);
            return gangRepository.save(gang);
        }).orElseThrow(() -> new GangNotFoundException(gangId));
    }


    @Override
    public void deleteById(Long id) {
        gangRepository.deleteById(id);
    }
}
