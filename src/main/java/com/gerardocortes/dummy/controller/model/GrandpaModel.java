package com.gerardocortes.dummy.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GrandpaModel {

    @NotBlank
    private String familyName;
    private String name;
    private String description;

}
