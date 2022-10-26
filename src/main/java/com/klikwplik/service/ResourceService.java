package com.klikwplik.service;

import com.klikwplik.entity.Resource;
import com.klikwplik.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public Optional<Resource> findByCoordinatesId(long id) {
        return resourceRepository.findByCoordinatesId(id);
    }

    public void deleteById(Long id) {
        resourceRepository.deleteById(id);
    }

}
