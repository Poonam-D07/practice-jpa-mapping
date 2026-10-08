package com.poonam.jpamapping.onetomany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

}
