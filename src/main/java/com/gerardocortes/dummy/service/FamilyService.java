package com.gerardocortes.dummy.service;

import com.gerardocortes.dummy.controller.exception.NotFoundException;
import com.gerardocortes.dummy.controller.model.GrandpaModel;
import com.gerardocortes.dummy.controller.model.ParentModel;
import com.gerardocortes.dummy.repository.GrandpaRepository;
import com.gerardocortes.dummy.repository.ParentRepository;
import com.gerardocortes.dummy.repository.entity.GrandpaEntity;
import com.gerardocortes.dummy.repository.entity.ParentEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyService {

    private final GrandpaRepository grandpaRepository;
    private final ParentRepository parentRepository;

    @Autowired
    public FamilyService(GrandpaRepository grandpaRepository, ParentRepository parentRepository) {
        this.grandpaRepository = grandpaRepository;
        this.parentRepository = parentRepository;
    }

    public String createGrandpa(final GrandpaModel grandpaModel) {
        GrandpaEntity grandpa = new GrandpaEntity();
        grandpa.setFamilyName(grandpaModel.getFamilyName());
        grandpa.setName(grandpaModel.getName());
        grandpa.setDescription(grandpaModel.getDescription());
        ParentEntity parent = new ParentEntity();
        grandpa.setParent(parent);
        GrandpaEntity save = grandpaRepository.save(grandpa);
        return save.getFamilyName();
    }

    public GrandpaModel readGrandpa(final String familyName) {
        GrandpaEntity grandpaEntity1 = grandpaRepository.findById(familyName).orElseThrow(NotFoundException::new);
        return new GrandpaModel(grandpaEntity1.getFamilyName(), grandpaEntity1.getName(), grandpaEntity1.getDescription());
    }

    public GrandpaModel updateGrandpa(final String familyName, final GrandpaModel grandpa) {
        final GrandpaEntity referenceById = grandpaRepository.getReferenceById(familyName);
        referenceById.setName(grandpa.getName());
        final GrandpaEntity grandpaEntity1 = grandpaRepository.save(referenceById);
        return new GrandpaModel(grandpaEntity1.getFamilyName(), grandpaEntity1.getName(), grandpaEntity1.getDescription());
    }

    public ParentModel readParent(final String familyName) {
        final ParentEntity parentEntity = parentRepository.findByGrandpa_FamilyName(familyName);
        return new ParentModel(parentEntity.getName(), parentEntity.getDescription());
    }

    public ParentModel updateParent(final String familyName, final ParentModel parentModel) {
        final ParentEntity parentEntity = parentRepository.getReferenceByGrandpa_FamilyName(familyName);
        parentEntity.setName(parentModel.getName());
        parentEntity.setDescription(parentModel.getDescription());
        parentRepository.save(parentEntity);
        return new ParentModel(parentEntity.getName(), parentEntity.getDescription());
    }
}
