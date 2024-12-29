package com.gerardocortes.dummy.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Parent")
@Data
@NoArgsConstructor
public class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY)
    @JoinColumn(name = "grandpa_family_name")
    private GrandpaEntity grandpa;

}
