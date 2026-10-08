package com.poonam.jpamapping.onetoone.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalRecordRequestDto {
    private String  bloodGroup;
    private String diagnosis;
    private String patientName;
    private Integer patientAge;
}
