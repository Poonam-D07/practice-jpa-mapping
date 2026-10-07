package com.poonam.jpamapping.onetoone.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "patients")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;

    //OWNING SIDE :  matlab jis table me foreign key column banta hai
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecordEntity medicalRecord;
}
