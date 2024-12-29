package com.gerardocortes.dummy.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Grandpa")
@Data
@NoArgsConstructor
public class GrandpaEntity {

    @Id
    private String familyName;
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private ParentEntity parent;

}
