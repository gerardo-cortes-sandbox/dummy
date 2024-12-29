package com.gerardocortes.dummy.repository;

import com.gerardocortes.dummy.repository.entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<ParentEntity, Long> {
    ParentEntity findByGrandpa_FamilyName(String grandpaFamilyName);
    ParentEntity getReferenceByGrandpa_FamilyName(String grandpaFamilyName);
}
