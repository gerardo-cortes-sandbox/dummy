package com.gerardocortes.dummy.controller;

import com.gerardocortes.dummy.controller.model.GrandpaModel;
import com.gerardocortes.dummy.controller.model.ParentModel;
import com.gerardocortes.dummy.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/family")
public class FamilyController {

    private final FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @PostMapping(value = "/grandpa")
    public ResponseEntity<String> postGrandpa(@Valid @RequestBody GrandpaModel grandpa) {
        final String familyName = familyService.createGrandpa(grandpa);
        return ResponseEntity
                .status(CREATED)
                .body(familyName);
    }

    @GetMapping(value = "/grandpa/{familyName}")
    public ResponseEntity<GrandpaModel> getGrandpa(@PathVariable String familyName) {
        final GrandpaModel grandpa = familyService.readGrandpa(familyName);
        return ResponseEntity
                .status(OK)
                .body(grandpa);
    }

    @PatchMapping(value = "/grandpa/{familyName}")
    public ResponseEntity<GrandpaModel> patchGrandpa(@PathVariable String familyName, @RequestBody GrandpaModel grandpa) {
        final GrandpaModel grandpaModel = familyService.updateGrandpa(familyName, grandpa);
        return ResponseEntity
                .status(OK)
                .body(grandpaModel);
    }

    @GetMapping(value = "/parent/{familyName}")
    public ResponseEntity<ParentModel> getParent(@PathVariable String familyName) {
        final ParentModel parent = familyService.readParent(familyName);
        return ResponseEntity
                .status(OK)
                .body(parent);
    }

    @PatchMapping(value = "/parent/{familyName}")
    public ResponseEntity<ParentModel> patchParent(@PathVariable String familyName, @RequestBody ParentModel parent) {
        final ParentModel parentModel = familyService.updateParent(familyName, parent);
        return ResponseEntity
                .status(OK)
                .body(parentModel);
    }
}
