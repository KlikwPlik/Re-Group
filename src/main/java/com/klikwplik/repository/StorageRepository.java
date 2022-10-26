package com.klikwplik.repository;

import com.klikwplik.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long> {

    List<Storage> findByGangId(long id);
}
