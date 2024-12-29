package com.gerardocortes.dummy.repository;

import com.gerardocortes.dummy.repository.entity.GrandpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrandpaRepository extends JpaRepository<GrandpaEntity, String> {
}
