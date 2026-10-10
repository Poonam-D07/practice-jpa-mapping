package com.poonam.jpamapping.onetomany.entity;

import com.poonam.jpamapping.manytomany.entity.SpecializationEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer experience;

    // INVERSE SIDE (foreign key yahan nahi, appointments table me hai)
    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    private List<AppointmentEntity>  appointments = new ArrayList<>();


    // MANY TO MANY (teesri table: doctor_specializations)
    @ManyToMany
    @JoinTable(
            name = "doctor_specializations",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    @Builder.Default
    private Set<SpecializationEntity> specializations = new HashSet<>();

}
