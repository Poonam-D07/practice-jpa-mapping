package com.poonam.jpamapping.onetoone.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medical_records")
public class MedicalRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodGroup;
    private String diagnosis;

    // INVERSE SIDE (foreign key yahan nahi, Patient ki table me hai)
    @OneToOne(mappedBy = "medicalRecord")
    private PatientEntity patientEntity;
}
