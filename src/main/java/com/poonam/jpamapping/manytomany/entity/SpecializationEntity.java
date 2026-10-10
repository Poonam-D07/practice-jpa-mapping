package com.poonam.jpamapping.manytomany.entity;

import com.poonam.jpamapping.onetomany.entity.DoctorEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specializations")
public class SpecializationEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    // INVERSE SIDE (teesri table Doctor wali taraf se sambhali ja rahi hai)
    @ManyToMany(mappedBy = "specializations")
    @Builder.Default
      private Set<DoctorEntity>doctors = new HashSet<>();
}
