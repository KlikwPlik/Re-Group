package com.klikwplik.service;

import com.klikwplik.entity.Storage;
import com.klikwplik.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    public List<Storage> findByGangId(long id) {
        return storageRepository.findByGangId(id);
    }

    public Storage saveStorage(Storage storage) {
        return storageRepository.save(storage);
    }

}
